package com.ahmed.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ahmed.model.Employee;
import com.ahmed.model.PunchInOut;


@Repository("punchDao")
public class PunchDaoImpl extends AbstractDao implements PunchDao {

	public void addPunch(PunchInOut p) {
		// TODO Auto-generated method stub
		persist(p);
	}

	public void updatePunch(PunchInOut p) {
		// TODO Auto-generated method stub
		getSession().update(p);
	}

	@SuppressWarnings("unchecked")
	public List<PunchInOut> listPunches() {
		// TODO Auto-generated method stub
		Criteria c = getSession().createCriteria(PunchInOut.class);
		return (List<PunchInOut>) c.list();
	}

	public void deletePunch(int pid) {
		// TODO Auto-generated method stub
		delete(getPunchById(pid));
	}

	public PunchInOut getPunchById(int pid) {
		// TODO Auto-generated method stub
		return (PunchInOut) getSession().get(PunchInOut.class, pid);
	}

	@SuppressWarnings("unchecked")
	public PunchInOut getPunchByIdDate(Employee e, Date punchDate) {
		// TODO Auto-generated method stub
		Query q = getSession().createQuery("FROM PunchInOut as P where P.employee = :empid and P.pDate = :punDate and pOut = null");
		q.setInteger("empid", e.getEid());
		q.setDate("punDate", punchDate);
		List<PunchInOut> result = (List<PunchInOut>) q.list();
		if(result.isEmpty()) {
			return null;
		}else {
			return result.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	public List<PunchInOut> punchesForEmployee(Employee e, Date start, Date end) {
		// TODO Auto-generated method stub
		Query q = getSession().createQuery("FROM PunchInOut as p where p.employee = :id and (pDate between :start and :end) order by p.pDate");
		q.setInteger("id", e.getEid());
		q.setDate("start", start);
		q.setDate("end", end);
		return (List<PunchInOut>) q.list();
	}

}
