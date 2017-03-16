# Spring REST Template Client Demo

#### Spring REST web service client demo 

On this page we will provide how to use spring RestTemplate to consume RESTful Web Service. RestTemplate communicates HTTP server using RESTful principals. RestTemplate provides different methods to communicate that will accept URI template, URI variables, response type and request object as arguments. It uses HTTP methods such as GET, POST, HEAD, PUT, DELETE etc. It also handles HTTP connections. In our example we will discuss consuming JSON and XML response. Now find the description of RestTemplate methods used in our example. 

**getForObject()** : It retrieves an entity using HTTP GET method on the given URL. 
**exchange()** : Executes the HTTP method for the given URI. It returns ResponseEntity. It can communicate using any HTTP method. 
**headForHeaders()** : Retrieves all headers. It uses HTTP HEAD method. 
**getForEntity()** : It retrieves an entity by using HTTP GET method for the given URL. It returns ResponseEntity. 
**delete()** : Deletes the resources at the given URL. It uses HTTP DELETE method. 
**put()**: It creates new resource or update for the given URL using HTTP PUT method. 
**postForObject()**: It creates new resource using HTTP POST method and returns an entity. 
**postForLocation()**: It creates new resource using HTTP POST method and returns the location of created new resource. 
**postForEntity()**: It creates news resource using HTTP POST method to the given URI template. It returns ResponseEntity.
 
Software Used
We are using following software in our example. 
1. Java 8 
2. Spring 4.3 
3. Gradle 3.3 
4. Tomcat 8.0 
5. IntelliJ IDEA

### getForObject() for JSON
Sever Response as JSON for the URL 

```aidl
http://localhost:8080/spring-rest/data/fetchjson/212
{
  "id" : 212,
  "name" : "Hendi Santika",
  "address" : {
    "village" : "Jawa Barat",
    "district" : "Cimahi",
    "state" : "UP"
  }
}
``` 

### getForObject() for XML
```aidl
http://localhost:8080/spring-rest/data/fetchxml/212
<company-info xmlns="com.hendisantika" id="212">
  <company-name>XYZ</company-name>
  <ceo-name>ABCD</ceo-name>
  <no-emp>100</no-emp>
</company-info> 
```

#### Java Bean used in REST Client Example
**Address.java**
```aidl
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
	private String village;
	private String district;
	private String state;
	public Address(){}
	public Address(String village, String district, String state){
		this.village = village;
		this.district = district;
		this.state = state;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
```

**Person.java**
```aidl
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
	private Integer id; 
	private String name;
  	private Address address;
  	public Person(){}
  	public Person(Integer id, String name, Address address){
  		this.id = id;
  		this.name = name;
  		this.address = address;
  	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
```

**Company.java**
```aidl
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="company-info", namespace="com.hendisantika" )
@XmlAccessorType(XmlAccessType.NONE)
public class Company {
	@XmlAttribute(name="id")
	private Integer id;
	@XmlElement(name="company-name")
	private String companyName;
	@XmlElement(name="ceo-name")
	private String ceoName;
	@XmlElement(name="no-emp")
	private Integer noEmp;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCeoName() {
		return ceoName;
	}
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	public Integer getNoEmp() {
		return noEmp;
	}
	public void setNoEmp(Integer noEmp) {
		this.noEmp = noEmp;
	}
}
```

**build.gradle**
```aidl
apply plugin: 'java'
apply plugin: 'eclipse'
archivesBaseName = 'rest-client'
version = '1.0-SNAPSHOT' 
repositories {
    mavenCentral()
}
dependencies {
    compile 'org.springframework.boot:spring-boot-starter-web:1.5.2.RELEASE'
}
```
  
**RestTemplate exchange()**
Server code.
```aidl
@RequestMapping(value="/exchange/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Person> exchangeData(@PathVariable(value = "id") Integer id) {
	Address address = new Address("Jawa Barat", "Cimahi", "UP");
	Person person = new Person(id,"Mahesh", address);
	return new ResponseEntity<Person>(person, HttpStatus.OK);
}	Client code.
public class ExchangeDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
	String uri = "http://localhost:8080/spring-rest/data/exchange/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("Hello World!", headers);
        ResponseEntity<Person> personEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, Person.class, 100);
        System.out.println("ID:"+personEntity.getBody().getId());
        System.out.println("Name:"+personEntity.getBody().getName());
        System.out.println("Village:"+personEntity.getBody().getAddress().getVillage());
    }
}
```

**Output.**
```aidl
ID:212
Name:Hendi Santika
Village:Jawa Barat
```

### RestTemplate headForHeaders()
**Server code.**
```aidl
@RequestMapping(value= "/fetch/{id}", method = RequestMethod.HEAD)
public ResponseEntity<Void> fetch(@PathVariable(value = "id") Integer id) {
	System.out.println("Id:"+ id);
	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
}
```

**Client code.**
```aidl
public class HeadForHeadersDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-rest/data/fetch/{id}";
        HttpHeaders httpHeaders = restTemplate.headForHeaders(url, 100);
        System.out.println(httpHeaders.getDate());
        System.out.println(httpHeaders.getContentType());
    }
}
```
**Output.**
```aidl
1487772028000
application/json
```

### RestTemplate getForEntity()
Server code.
```aidl
@RequestMapping("/fetch/{name}/{village}")
public Person getPersonDetail(@PathVariable(value = "name") String name,
                                    @PathVariable(value = "village") String village) {
	Address address = new Address(village, "Jawa Barat", "UP");
	return new Person(100,name, address);
}
```

Client code.
```aidl
public class GetForEntityDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-rest/data/fetch/{name}/{village}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "Hendi Santika");
        map.put("village", "Jawa Barat");
        ResponseEntity<Person> personEntity = restTemplate.getForEntity(url, Person.class, map);
        System.out.println("Name:"+personEntity.getBody().getName());
        System.out.println("Village:"+personEntity.getBody().getAddress().getVillage());
    }
}
```

```aidl
Output.
Name:Mahesh
Village:Dhananjaypur  
```

### RestTemplate delete()
Server Code.
```aidl
@RequestMapping(value="/delete/{name}/{village}", method = RequestMethod.DELETE)
public void deleteData(@PathVariable(value = "name") String name,
	      @PathVariable(value = "village") String village) {
	System.out.println("Delete person with name:"+ name+ " and village:"+ village);
}
```

Client code.
```aidl
public class DeleteDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-rest/data/delete/{name}/{village}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "Hendi Santika");
        map.put("village", "Jawa Barat");
        restTemplate.delete(url, map);
    }
}
``` 

### RestTemplate put()
Server code.
```aidl
@RequestMapping(value="/putdata/{id}/{name}", method = RequestMethod.PUT)
public void putData(@PathVariable(value = "id") String id,
		           @PathVariable(value = "name") String name, @RequestBody Address address) {
	System.out.println("Id:"+ id+ " Name:"+ name);
	System.out.println("District:"+ address.getDistrict());
	System.out.println("Village:"+ address.getVillage());		
}
```

Client code.
```aidl
public class PutDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-rest/data/putdata/{id}/{name}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "100");
        map.put("name", "Ram");
        Address address = new Address("Dhananjaypur", "Varanasi","UP");
        restTemplate.put(url, address, map);
    }
}
```  
##RestTemplate postForObject()
Server code.
```aidl
@RequestMapping(value="/saveinfo/{id}/{name}", method = RequestMethod.POST)
public ResponseEntity<Person> saveInfo(@PathVariable(value = "id") Integer id,
		           @PathVariable(value = "name") String name, @RequestBody Address address) {
	Person person = new Person(id, name, address);
	return new ResponseEntity<Person>(person, HttpStatus.CREATED);
}
```


Client code.
```aidl
public class PostForObjectDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-rest/data/saveinfo/{id}/{name}";
        Map>String, String> map = new HashMap<String, String>();
        map.put("id", "111");
        map.put("name", "Shyam");
	Address address = new Address("Jawa Barat", "Cimahi", "UP");
        Person person= restTemplate.postForObject(url, address, Person.class, map);
        System.out.println(person.getName());
        System.out.println(person.getAddress().getVillage());
    }
}
```

```aidl
Output.
Hendi Santika
Jawa Barat

```
### RestTemplate postForLocation()
Server code.
```aidl
@RequestMapping(value="/location/{id}/{name}", method = RequestMethod.POST)
public ResponseEntity<Void> locationURI(@PathVariable(value = "id") Integer id,
		           @PathVariable(value = "name") String name, @RequestBody Address address,
		           UriComponentsBuilder builder) {
	System.out.println("Id:"+ id+ " Name:"+ name);
	System.out.println("Village:"+ address.getVillage()+ " District:"+ address.getDistrict());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/location/{id}/{name}").buildAndExpand(id, name).toUri());
	return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
}
```

Client code.
```aidl
public class PostForLocationDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-rest/data/location/{id}/{name}";
	Address address = new Address("Jawa Barat", "Cimahi", "UP");
	URI uri= restTemplate.postForLocation(url, address, 212, "Cimahi");
	System.out.println(uri.getPath());
    }
}
```

```aidl
Output.
/spring-rest/location/212/Cimahi
```
 
### RestTemplate postForEntity()
Server code.
```aidl
@RequestMapping(value="/saveinfo/{id}/{name}", method = RequestMethod.POST)
public ResponseEntity<Person> saveInfo(@PathVariable(value = "id") Integer id,
		           @PathVariable(value = "name") String name, @RequestBody Address address) {
	Person person = new Person(id, name, address);
	return new ResponseEntity<Person>(person, HttpStatus.CREATED);
}
```

Client code.
```aidl
public class PostForEntityDemo {
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring-rest/data/saveinfo/{id}/{name}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "212");
        map.put("name", "Cimahi");
	Address address = new Address("Jawa Barat", "Cimahi", "UP");
        ResponseEntity<Person> entity= restTemplate.postForEntity(url, address, Person.class, map);
        System.out.println(entity.getBody().getName());
        System.out.println(entity.getBody().getAddress().getVillage());
    }
}
```

```aidl
Output.
Cimahi
Jawa Barat
``` 
###Spring REST Web Service Server Code
Find spring REST web service server code used in our example. Here **Address** and **Person** class are same as used in our spring REST client code. 

**PersonController.java**
```aidl
package com.hendisantika.controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.hendisantika.entity.Address;
import com.hendisantika.entity.Company;
import com.hendisantika.entity.Person;
@RestController
@RequestMapping("/data")
public class PersonController {
	@RequestMapping(value= "/fetchjson/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Person getForObjectJsonDemo(@PathVariable(value = "id") Integer id) {
		Address address = new Address("Jawa Barat","Cimahi", "UP");
		return new Person(id, "Bandung", address);
	}
	
	@RequestMapping(value= "/fetchxml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	public Company getForObjectXMLDemo(@PathVariable(value = "id") Integer id) {
		Company comp = new Company();
		comp.setId(id);
		comp.setCompanyName("XYZ");
		comp.setCeoName("ABCD");
		comp.setNoEmp(100);
		return comp;
	}	
	
	@RequestMapping("/fetch/{name}/{village}")
	public Person getPersonDetail(@PathVariable(value = "name") String name,
			                                    @PathVariable(value = "village") String village) {
		Address address = new Address(village, "Cirengit", "UP");
		return new Person(100, name, address);
	}
	
	@RequestMapping(value="/exchange/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> exchangeData(@PathVariable(value = "id") Integer id) {
		Address address = new Address("Jawa Barat", "Cimahi", "UP");
		Person person = new Person(id, "Johny", address);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
		
	@RequestMapping(value="/delete/{name}/{village}", method = RequestMethod.DELETE)
	public void deleteData(@PathVariable(value = "name") String name,
                      @PathVariable(value = "village") String village) {
		System.out.println("Delete person with name:"+ name+ " and village:"+ village);
	}
	
	@RequestMapping(value= "/fetch/{id}", method = RequestMethod.HEAD)
	public ResponseEntity<Void> fetch(@PathVariable(value = "id") Integer id) {
		System.out.println("Id:"+ id);
		HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
	}
		
	@RequestMapping(value="/putdata/{id}/{name}", method = RequestMethod.PUT)
	public void putData(@PathVariable(value = "id") String id,
			           @PathVariable(value = "name") String name, @RequestBody Address address) {
		System.out.println("Id:"+ id+ " Name:"+ name);
		System.out.println("District:"+ address.getDistrict());
		System.out.println("Village:"+ address.getVillage());		
	}
	
	@RequestMapping(value="/saveinfo/{id}/{name}", method = RequestMethod.POST)
	public ResponseEntity<Person> saveInfo(@PathVariable(value = "id") Integer id,
			           @PathVariable(value = "name") String name, @RequestBody Address address) {
		Person person = new Person(id, name, address);
		return new ResponseEntity<Person>(person, HttpStatus.CREATED);
	}
		
	@RequestMapping(value="/location/{id}/{name}", method = RequestMethod.POST)
	public ResponseEntity<Void> locationURI(@PathVariable(value = "id") Integer id,
			           @PathVariable(value = "name") String name, @RequestBody Address address,
			           UriComponentsBuilder builder) {
		System.out.println("Id:"+ id+ " Name:"+ name);
		System.out.println("Village:"+ address.getVillage()+ " District:"+ address.getDistrict());
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/location/{id}/{name}").buildAndExpand(id, name).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}	
}
```

**AppConfig.java**
```aidl
package com.hendisantika.config;  
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration 
@ComponentScan("com.hendisantika") 
@EnableWebMvc   
public class AppConfig extends WebMvcConfigurerAdapter {  
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setPrettyPrint(true);
        return mappingJackson2HttpMessageConverter;
    }
    
    @Bean
    public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter() {
    	MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter = new MappingJackson2XmlHttpMessageConverter();
    	mappingJackson2XmlHttpMessageConverter.setPrettyPrint(true);
        return mappingJackson2XmlHttpMessageConverter;
    }
        
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);        
        converters.add(mappingJackson2HttpMessageConverter());
        converters.add(mappingJackson2XmlHttpMessageConverter());
    }
} 
```

**Company.java**
```java
package com.hendisantika.entity;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
@JacksonXmlRootElement(localName="company-info", namespace="com.hendisantika")
public class Company {
	@JacksonXmlProperty(localName="id", isAttribute=true)
	private Integer id;
	
	@JacksonXmlProperty(localName="company-name")
	private String companyName;
	
	@JacksonXmlProperty(localName="ceo-name")
	private String ceoName;
	
	@JacksonXmlProperty(localName="no-emp")
	private Integer noEmp;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCeoName() {
		return ceoName;
	}
	
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	
	public Integer getNoEmp() {
		return noEmp;
	}
	
	public void setNoEmp(Integer noEmp) {
		this.noEmp = noEmp;
	}
}
```

**WebAppInitializer.java**
```java
package com.hendisantika.config;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		 return new Class[] { AppConfig.class };
	}
	
	@Override
        protected Class<?>[] getServletConfigClasses() {
                 return null;
        }
    
    @Override
    protected String[] getServletMappings() {
                 return new String[]{ "/" };
        } 
}
```

**build.gradle**
```
apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'idea'
apply plugin: 'war'
war.archiveName 'spring-rest.war'
repositories {
    mavenCentral()
}
dependencies {
    compile 'org.springframework.boot:spring-boot-starter-web:1.5.2.RELEASE'
    compile 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.6.2'
    compile 'org.codehaus.woodstox:woodstox-core-asl:4.4.1'
    providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat:1.5.1.RELEASE'
}
``` 



