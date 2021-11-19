package com.coherent.unnamed.logic.service;

import java.sql.Date;
import java.util.List;

import com.coherent.unnamed.logic.dto.LogicTransctionDTO;
import com.coherent.unnamed.logic.response.BaseResponse;
import com.coherent.unnamed.logic.response.BaseResponseLogbydate;
import com.coherent.unnamed.logic.response.BaseResponseVerifylocation;
import com.coherent.unnamed.logic.response.BaseResponsetTimelogsRegAtten;

public interface LogicService {

	String saveLogic(LogicTransctionDTO logicTransaction);

	List<LogicTransctionDTO> getAllLogic();

	BaseResponse listbydaysbymonth(int year, int month);


	BaseResponseVerifylocation verifylocation(Double lngt, Double lat) throws Exception;

	BaseResponseLogbydate logsbydate(Date date,int user_id);


	BaseResponsetTimelogsRegAtten registerattendance(Double lngt, Double lat, int punch);

	//BaseResponse hoursandpresent(Date date);

	void calculatehours();
}
