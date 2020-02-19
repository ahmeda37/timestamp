package com.ahmed.main.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ahmed.main.model.Employee;
import com.ahmed.main.model.PunchInOut;

public interface PunchRepo extends CrudRepository<PunchInOut, Integer> {
	@Query("FROM PunchInOut as P where P.employee = :e and P.pDate = :punchDate and P.pOut = null")
	public PunchInOut getPunchByIdDate(@Param("e") Employee e, @Param("punchDate") Date punchDate);

	@Query("FROM PunchInOut as p where p.employee = :e and (p.pDate between :start and :end) order by p.pDate")
	public List<PunchInOut> punchesForEmployee(@Param("e") Employee e, @Param("start") Date start, @Param("end") Date end);
}
