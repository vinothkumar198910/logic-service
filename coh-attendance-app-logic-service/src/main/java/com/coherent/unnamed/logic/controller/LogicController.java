package com.coherent.unnamed.logic.controller;

import java.sql.Date;
import java.util.List;

import com.coherent.unnamed.logic.response.BaseResponse;
import com.coherent.unnamed.logic.response.BaseResponseLogbydate;
import com.coherent.unnamed.logic.response.BaseResponseVerifylocation;
import com.coherent.unnamed.logic.response.BaseResponsetTimelogsRegAtten;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.coherent.unnamed.logic.dto.LogicTransctionDTO;
import com.coherent.unnamed.logic.service.LogicService;


@RestController
@EnableScheduling
@RequestMapping(value ="/logic")
public class LogicController {
	
	@Autowired
	private LogicService logicService;



	
	 @PostMapping(value = "/saveLogic", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<String> saveCountry(@RequestBody LogicTransctionDTO logicTransaction) {
		 
	      return new ResponseEntity<String>(logicService.saveLogic(logicTransaction), HttpStatus.OK);
	      
	 }
	 
	 

	 @GetMapping(value = "/getAllLogic", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<LogicTransctionDTO>> getAllLogic() {
		 
	      return new ResponseEntity<List<LogicTransctionDTO>>(logicService.getAllLogic(), HttpStatus.OK);
	      
	 }

	 @GetMapping(value="/listbydaysbymonth")
	 public BaseResponse listbydaysbymonth(@RequestParam(value="year") int year , @RequestParam(value="month") int month){
		 return logicService.listbydaysbymonth(year, month);
	 }

	 @GetMapping(value="/verifylocation")
	public BaseResponseVerifylocation verifylocation(@RequestParam(value="lngt") Double lngt , @RequestParam(value="lat") Double lat) throws Exception {
		 return logicService.verifylocation(lngt,lat);
	 }

	 @GetMapping(value="/logsbydate")
	public BaseResponseLogbydate logsbydate(@RequestParam(value="date") Date date,@RequestParam(value="user_id") int user_id ){
		 return logicService.logsbydate(date,user_id);
	 }

	 @GetMapping(value = "/registerattendance")
	public BaseResponsetTimelogsRegAtten registerattendance(@RequestParam(value="lngt") Double lngt, @RequestParam(value="lat") Double lat, @RequestParam(value="punch") int punch){
		 return logicService.registerattendance(lngt,lat, punch);
	 }


//	@GetMapping(value="/hoursandpresent")
//	public BaseResponse hoursandpresent(@RequestParam(value="date") Date date){
//		return logicService.hoursandpresent(date);
//	}

	//@Scheduled(fixedDelay = 1000)
	@GetMapping(value="/calculatehours")
	public void calculatehours(){
		  logicService.calculatehours();
	}

}
