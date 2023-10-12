package com.example.demo;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.util.Iterable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class DemoService {
    private static final Logger logger = LoggerFactory.getLogger(DemoService.class);
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public String insertDepartment(Long deptId, String deptDescription, String deptName) {
        if (departmentRepository.findById(deptId).isPresent()) {
            logger.info("!!! 동일한 값이 이미 있습니다. !!!");
            return "동일한 값이 이미 있습니다.";
        }
        Department department = Department.builder().deptId(deptId).deptDescription(deptDescription).deptName(deptName).createTime(null)
                .build();
        departmentRepository.save(department);
        logger.info("!!! Insert 완료. !!!");
        return "deptId : " + deptId + "deptDescription : " + deptDescription + "deptName :" + deptName;
    }

    public String insertUser(Long userId, String userName, String userDescription, Long deptId) {
        if (departmentRepository.findById(userId).isPresent()) {
            logger.info("!!! 동일한 값이 이미 있습니다. !!!");
            return "동일한 값이 이미 있습니다.";
        }
        Department department = departmentRepository.getById(deptId);
        User user = User.builder().userId(userId).userName(userName).userDescription(userDescription).createTime(null)
                .department(department)
                .build();
        userRepository.save(user);
        logger.info("!!! Insert완료. !!!");
        return "userId : " + userId + "userName : " + userName + "userDescription :" + userDescription + "deptId"
                + deptId;
    }

    public String selectUserByQuery(Long userId){
        Map userMap = userRepository.findUserbyUserId(userId);
        logger.info("!!! userRepository.findUserDept(userId) is {} !!!", userMap);
        ObjectMapper mapper = new ObjectMapper();
        String userString="EMPTY";
        try {
            userString = mapper.writeValueAsString(userMap);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return userString;
    }

    public String selectUserByJpa(Long userId){
        Optional<User> user = userRepository.findById(userId);
        String returnString="EMPTY";

        if(!user.isPresent()) {
            throw new IllegalArgumentException();
        }
        String departmentNameValue = user.get().getDepartment().getDeptName();
        String userNameValue = user.get().getUserName();

        // logger.info("!!! selectUserByJpa value  : {} !!!", departmentNameValue); 

        try {
            returnString = "Username : " + userNameValue + ", Department : " + departmentNameValue;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return returnString;
    }
}
