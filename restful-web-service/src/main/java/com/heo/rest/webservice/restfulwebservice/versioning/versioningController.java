package com.heo.rest.webservice.restfulwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;

public class versioningController {
	
	
	/* url을 통한 versioning
	 * // v1
	 * 
	 * @GetMapping("/v1/person") public PersonV1 getFirstVersionOfPerson(){ return
	 * new PersonV1("Bob CHarile"); }
	 * 
	 * // v2
	 * 
	 * @GetMapping("/v2/person") public PersonV2 getSecondVersionOfPerson(){ return
	 * new PersonV2(new Name("Bob","Charile")); }
	 */
	
	
	/* Request parameter를 통한 versioning
	 * // v1
	 * 
	 * @GetMapping(path="/person", params="version=1") public PersonV1
	 * getFirstVersionOfPerson(){ return new PersonV1("Bob CHarile"); }
	 * 
	 * // v2
	 * 
	 * @GetMapping(path="/person", params="version=2") public PersonV2
	 * getSecondVersionOfPerson(){ return new PersonV2(new Name("Bob","Charile")); }
	 */
	
	/* 헤더를 통한 versioning
	 * // v1
	 * 
	 * @GetMapping(path="/person", headers="X-API-VERSION=1") public PersonV1
	 * getFirstVersionOfPerson(){ return new PersonV1("Bob CHarile"); }
	 * 
	 * // v2
	 * 
	 * @GetMapping(path="/person", headers="X-API-VERSION=1") public PersonV2
	 * getSecondVersionOfPerson(){ return new PersonV2(new Name("Bob","Charile")); }
	 */
	
	// MediaType을 통한 versioning
	// v1
    @GetMapping(path="/person", produces="application/vnd.company.app-v1+json")
    public PersonV1 getFristVersionOfPerson(){
    	return new PersonV1("Bob CHarile");
    }
    
    // v2
    @GetMapping(path="/person", produces="application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPerson(){
    	return new PersonV2(new Name("Bob", "Charile"));
    }
    
}
