package com.heo.rest.webservice.restfulwebservice.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class filteringController {
	/* 정적 필터링 시, 콘트롤러 메서드에서는 크게 영향 X
	 * @GetMapping("/filtering") public SomeBean filtering(){ return new
	 * SomeBean("val1","val2", "val3"); }
	 * 
	 * @GetMapping("filtering-list") public List<SomeBean> filteringList(){ return
	 * Arrays.asList(new SomeBean("val1","val2", "val3"), new
	 * SomeBean("val4","val5", "val6")); }
	 */
	
	// 동적 필터링
	@GetMapping("/filtering")
    public MappingJacksonValue filtering(){
    	SomeBean someBean = new SomeBean("val1","val2", "val3");
        
    	MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean); 
        // MappingJacksonValue에 내가 응답할 값을 전달
        
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3"); 
        // 필터 생성, 인자로 전달한 값들만 응답함
        
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter); 
        // 필터 프로바이더를 통해 필터를 SomeBean에 적용 
        // 해당 필터의 이름 "SomeBeanFilter"가 SomeBean의 @JsonFilter()로 설정한 이름과 같아야 적용된다.
        
        mappingJacksonValue.setFilters(filters);
        
    	return mappingJacksonValue;
    }
    
    
    
    @GetMapping("filtering-list")
    public MappingJacksonValue filteringList(){
    	List<SomeBean> list = Arrays.asList(new SomeBean("val1","val2", "val3"), 
        									new SomeBean("val4","val5", "val6"));
    
    	MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list); 
    
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2"); 
        
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter); 
        
        mappingJacksonValue.setFilters(filters);
        
    	return mappingJacksonValue;
    }
}
	