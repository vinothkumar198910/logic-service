package com.coherent.unnamed.logic.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.*;


import com.coherent.unnamed.logic.entity.Users;
import com.coherent.unnamed.logic.repository.UsersRepository;
import org.joda.time.LocalDate;
import org.modelmapper.ModelMapper;
import com.coherent.unnamed.logic.Constant.Constant;
import com.coherent.unnamed.logic.dto.*;
import com.coherent.unnamed.logic.entity.Attendence;
import com.coherent.unnamed.logic.entity.TimeLogs;
import com.coherent.unnamed.logic.repository.AttendenceRepository;
import com.coherent.unnamed.logic.response.*;
import com.coherent.unnamed.logic.repository.TimeLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.coherent.unnamed.logic.model.LogicTransaction;
import com.coherent.unnamed.logic.repository.LogicRepository;
import com.coherent.unnamed.logic.service.LogicService;

@Service
public class LogicServiceImpl implements LogicService{

	@Autowired
	private LogicRepository logicRepository;

	@Autowired
	private TimeLogsRepository timeLogsRepository;

	@Autowired
	private AttendenceRepository attendenceRepository;


	@Autowired
	private UsersRepository usersRepository;


	@Override
	public String saveLogic(LogicTransctionDTO logicTransaction) {

		if (logicTransaction!= null) {
			
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			
			LogicTransaction logicTrxn = new LogicTransaction();
			
			logicTrxn.setCommodity(logicTransaction.getCommodity());
			logicTrxn.setGrade(logicTransaction.getGrade());
			logicTrxn.setPrice(logicTransaction.getPrice());
			logicTrxn.setQuantity(logicTransaction.getQuantity());
			logicTrxn.setTotal(logicTransaction.getTotal());
			logicTrxn.setActive(true);
			logicTrxn.setDeletedFlag(false);
			logicTrxn.setCreatedAt(timeStamp);
			logicTrxn.setCreatedBy(1L);
			logicTrxn.setModifiedAt(timeStamp);
			logicTrxn.setModifiedBy(1L);
			
			logicRepository.save(logicTrxn);
			
			return "Success";
			
		}else {
			
			return "Error";
		}

	}

	@Override
	public List<LogicTransctionDTO> getAllLogic() {
		
		List<LogicTransctionDTO> outputList = new ArrayList<>();
		
		List<LogicTransaction> logicTrxnList = logicRepository.findAll();
		
		if(logicTrxnList!=null) {
			
			LogicTransctionDTO logicTrxnDto = null;
			
			for (LogicTransaction dnLogic: logicTrxnList) {
				
				logicTrxnDto = new LogicTransctionDTO();
				logicTrxnDto.setId(dnLogic.getId());
				logicTrxnDto.setCommodity(dnLogic.getCommodity());
				logicTrxnDto.setGrade(dnLogic.getGrade());
				logicTrxnDto.setPrice(dnLogic.getPrice());
				logicTrxnDto.setQuantity(dnLogic.getQuantity());
				logicTrxnDto.setTotal(dnLogic.getTotal());
				logicTrxnDto.setActive(dnLogic.isActive());
				logicTrxnDto.setDeletedFlag(dnLogic.isDeletedFlag());
				
				outputList.add(logicTrxnDto);
				
			}
		}
		
		return outputList;
	}

	@Override
	public BaseResponse listbydaysbymonth(int year, int month) {
		BaseResponse baseResponse= new BaseResponse();
		List<Attendencedata> attendencedata1 = new ArrayList<>();
		//return ;
		try {
			List<Attendence> attendence =  attendenceRepository.findByCreatedatYearAndMonthIn(year, month);
			attendence.stream().forEachOrdered(attendence1 -> {
				Attendencedata attendencedata = new Attendencedata();
				attendencedata.setIs_present(attendence1.getIs_present());
				attendencedata.setCreated_at(attendence1.getCreated_at());
				attendencedata.setCreated_by(attendence1.getCreated_by());
				attendencedata1.add(attendencedata);
			});
			baseResponse.setStatusCode("200");
			baseResponse.setStatusMessage("success");
			baseResponse.setData(attendencedata1);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return baseResponse;
	}

	@Override
	public BaseResponseVerifylocation verifylocation(Double lngt, Double lat) throws Exception {
		BaseResponseVerifylocation baseResponseVerifylocation = new BaseResponseVerifylocation();
		//new ArrayList<>();
		APIResponse apiResponse = new APIResponse();
		//boolean errors;
		if(!(lngt.equals(Constant.LONGITUDE)&&(lat.equals(Constant.LATITUDE)))) {
			//boolean errors = true;
			throw new BadRequestException(Constant.VALUEDOESNOTMATCH, HttpStatus.BAD_REQUEST);
		}else{
			baseResponseVerifylocation.setStatusCode("200");
			baseResponseVerifylocation.setStatusMessage("success");
			baseResponseVerifylocation.setInRange(1);
		}
		return baseResponseVerifylocation;
	}

	@Override
	public BaseResponseLogbydate logsbydate(Date date,int user_id){
		BaseResponseLogbydate baseResponseLogbydate= new BaseResponseLogbydate();
		List<LogsbydatesdataDTO> logsbydatesdataDTOS = new ArrayList<>();
		List<LogsDTO> logsDTOS = new ArrayList<>();
		List<Attendence> attendence =  attendenceRepository.findByCreatedatDateAndUserId(date,user_id);

		attendence.stream().forEachOrdered(attendence1 -> {
			LogsbydatesdataDTO logsbydatesdataDTO = new LogsbydatesdataDTO();
			baseResponseLogbydate.setHours(attendence1.getHours());
			baseResponseLogbydate.setDate(date);

			//logsbydatesdataDTO.setUsers(attendence1.getUser());
			List<TimeLogs> timeLogs =  timeLogsRepository.findByUser_id_fk(attendence1.getUsers(),attendence1.getCreated_at().toLocalDateTime().toLocalDate());
			timeLogs.stream().forEachOrdered(timeLogs1 -> {
				LogsDTO logs = new LogsDTO();
				logs.setCreated_at(timeLogs1.getCreatedat());
				logs.setCreated_by(timeLogs1.getCreated_by());
				logs.setIs_logged(timeLogs1.getIs_logged());
				logsDTOS.add(logs);
			});

			logsbydatesdataDTOS.add(logsbydatesdataDTO);
		});

		baseResponseLogbydate.setStatusCode("200");
		baseResponseLogbydate.setStatusMessage("success");
		baseResponseLogbydate.setData(logsDTOS);


		return baseResponseLogbydate;
	}

	@Override
	public BaseResponsetTimelogsRegAtten registerattendance(Double lngt, Double lat, int punch){
		BaseResponsetTimelogsRegAtten baseResponsetTimelogsRegAtten= new BaseResponsetTimelogsRegAtten();
		List<TimeLogs> timelogs =  new LinkedList<>();
		TimeLogs timeLogs = new TimeLogs();
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		timeLogs.setLatitude(lat);
		timeLogs.setLongitude(lngt);
		timeLogs.setCreated_by("USER");
		timeLogs.setIs_logged(1);
		timeLogs.setIs_active(1);
		timeLogs.setIs_delete(0);
		timeLogs.setCreatedat(timeStamp);
		timeLogs.setModified_at(timeStamp);
		timeLogsRepository.save(timeLogs);

		baseResponsetTimelogsRegAtten.setStatusCode("200");
		baseResponsetTimelogsRegAtten.setStatusMessage("success");



		return baseResponsetTimelogsRegAtten;
	}

//	@Override
//	public BaseResponse hoursandpresent(Date date){
//		List<LogsbydatesdataDTO> logsbydatesdataDTOS = new ArrayList<>();
//		List<LogsDTO> logsDTOS = new ArrayList<>();
//		BaseResponse baseResponse= new BaseResponse();
//		List<Attendence> attendence =  attendenceRepository.findByCreatedatDate(date);
//		List<Attendence> attendences = new LinkedList<>();
//		attendence.stream().forEachOrdered(attendence1 -> {
//			Attendence attendence2 = new Attendence();
//			ModelMapper modelMapper = new ModelMapper();
//
//			if(attendence1.getHours() > 4){
//				Attendence attendence3 = modelMapper.map(attendence1, Attendence.class);
//
//				attendence3.setId(attendence1.getId());
//				attendence3.setIs_present(1);
//
//				attendenceRepository.save(attendence3);
//			}
//
//		});
//		baseResponse.setStatusCode("200");
//		baseResponse.setStatusMessage("success");
//		//baseResponse.setData(attendence3);
//		return baseResponse;
//	}

	private Date getMeYesterday(){
		return new Date(System.currentTimeMillis()-24*60*60*1000);
	}

	@Override
	public void calculatehours(){
		BaseResponse baseResponse= new BaseResponse();

		List<Attendence> attendences = new LinkedList<>();
		System.out.println(getMeYesterday());


		ArrayList<Long> l = new ArrayList<>();
		ArrayList<Long> b = new ArrayList<>();
		//Integer l=0;
		ZoneId fromTimeZone = ZoneId.of("Asia/Kolkata");

		//long usercount = usersRepository.Count();
		List<Users> users = usersRepository.findAll();
		final long[] h = {0};
		users.stream().forEachOrdered(users1 -> {
			ArrayList<Long> lis1 = new ArrayList<>();
			ArrayList<Long> lis2 = new ArrayList<>();
			int userid = users1.getId();
		List<TimeLogs> timeLogs =  timeLogsRepository.findByUserId(users1.getId(),getMeYesterday());
		long time1 = timeLogs.stream().filter(timeLogs1 -> timeLogs1.getIs_logged() == 1).mapToLong(timeLogs1 ->
		{
			lis2.add((long) timeLogs1.getCreatedat().toLocalDateTime().toLocalTime().getHour());
			return timeLogs1.getCreatedat().toLocalDateTime().toLocalTime().getHour();
		}).sum();
		long time2 = timeLogs.stream().filter(timeLogs1 -> timeLogs1.getIs_logged() == 0).mapToLong(timeLogs1 -> {
			if(timeLogs1.getCreated_by().equals("SYSTEM")){
				lis1.add(0L);
				return 0;
			}else{
				lis1.add((long) timeLogs1.getCreatedat().toLocalDateTime().toLocalTime().getHour());
				return timeLogs1.getCreatedat().toLocalDateTime().toLocalTime().getHour();
			}
			}).sum();

			long c = 0;
			for(int k = 0; k < lis1.size(); k++) {
			if(lis1.get(k)==0L)
				{
					c = c + 0;
					//l.add(c);
				}else{
					 c = c + lis1.get(k) - lis2.get(k);
				}

			}
			l.add(c);

		System.out.println(l.get((int) h[0]));

			Attendence attendence2 = new Attendence();
			attendence2.setCreated_at(new Timestamp(getMeYesterday().getTime()));
			attendence2.setModified_at(new Timestamp(getMeYesterday().getTime()));


					attendence2.setHours(l.get((int) h[0]));

			if(attendence2.getHours()>6){
				attendence2.setIs_present(1);
			}else{
				attendence2.setIs_present(0);
			}
			attendence2.setUsers(users1);
			attendence2.setIs_delete(0);
			attendence2.setIs_active(1);
			attendence2.setCreated_by("");
			attendence2.setModified_by("SYSTEM");
			attendenceRepository.save(attendence2);

			h[0]++;
		});

		baseResponse.setStatusCode("200");
		baseResponse.setStatusMessage("success");
		System.out.println("Cron running");
	}

}
