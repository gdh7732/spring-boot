package com.example.demo.dao;

import com.example.demo.entity.JobAndTrigger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author guodahai
 */
public interface JobAndTriggerRepository extends JpaRepository<JobAndTrigger, Long> {

}
