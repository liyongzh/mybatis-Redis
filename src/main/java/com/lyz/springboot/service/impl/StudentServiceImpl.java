package com.lyz.springboot.service.impl;

import com.lyz.springboot.mapper.StudentMapper;
import com.lyz.springboot.model.Student;
import com.lyz.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李勇志
 * @create 2019-11-03 9:20
 */
@Service
public class StudentServiceImpl implements StudentService {
   @Autowired
    private StudentMapper studentMapper;

    /* @Override
    public List<Student> getAllStudent() {

    return studentMapper.selectAllStudent();
    }*/

    /*注入springboot自动配置好的RedisTemplate*/
     @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Override
    public List<Student> getAllStudent() {
        //字符串序列化器
        RedisSerializer redisSerializer =new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        //查询缓存
        List<Student> studentList= ( List<Student>) redisTemplate.opsForValue().get("allStudents");
        if(null==studentList){
            //缓存为空，查询一遍数据库
            studentList = studentMapper.selectAllStudent();
            //把数据库查询出来的数据，放入Redis中
            redisTemplate.opsForValue().set("allStudents",studentList);
        }
        return studentList;
    }

}
