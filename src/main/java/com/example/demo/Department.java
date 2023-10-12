package com.example.demo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Department {
    private static final Logger logger = LoggerFactory.getLogger(Department.class);

    @Id
    @Column(name = "deptId")
    private Long deptId;

    @OneToMany(mappedBy = "department")
    private List<User> user = new ArrayList<User>();

    @CreationTimestamp
	@Column(name = "createTime")
	private LocalDateTime createTime;
    
    private String deptName;    
    private String deptDescription;
}
