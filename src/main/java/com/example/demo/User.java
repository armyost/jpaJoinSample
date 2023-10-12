package com.example.demo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// @NoArgsConstructor
public class User {
    private static final Logger logger = LoggerFactory.getLogger(User.class);

    @Id
    @Column(name = "userId")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptId")
    private Department department; 
    
    private String userName;    
    private String userDescription;

    @CreationTimestamp
	@Column(name = "createTime")
	private LocalDateTime createTime;
}