package dao;

import java.util.ArrayList;

import dto.testDto;

public class testDao {
	
	public int getTotal(int a, int b) {
		int total = a+b;
		return total;
	}
	public ArrayList<testDto> getLsit(){
		ArrayList<testDto> dtos = new ArrayList<>();
		
		testDto dto1 = new testDto("장성혁", "도쿄", 50);
		testDto dto2 = new testDto("김주성", "대구", 50);
		
		dtos.add(dto1);
		dtos.add(dto2);
		
		return dtos;
	}
}
