package com.github.yiyan1992.carloan.dao.school;

import com.github.yiyan1992.carloan.entity.school.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SchoolClassDao extends JpaRepository<SchoolClass, Integer> {
}
