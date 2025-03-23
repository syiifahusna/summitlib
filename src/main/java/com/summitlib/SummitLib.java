package com.summitlib;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.summitlib.exceptionhandling.ConstraintViolationExceptionMapper;

@ApplicationPath("/api")
public class SummitLib extends Application{
	
//	@Override
//	public Set<Object> getSingletons() {
//	    Set<Object> singletons = new HashSet<>();
//	    singletons.add(new ConstraintViolationExceptionMapper());
//	    return singletons;
//	}

}
