package snippet;

public class Snippet {
	<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-test</artifactId>
				<scope>test</scope>
			</dependency>
			<dependency>
				<groupId>org.springframework.security</groupId>
				<artifactId>spring-security-test</artifactId>
				<scope>test</scope>
			</dependency>
			
			<!-- JWT API -->
	<dependency>
	    <groupId>io.jsonwebtoken</groupId>
	    <artifactId>jjwt-api</artifactId>
	    <version>0.12.7</version>
	</dependency>
	
	<!-- JWT Implementation -->
	<dependency>
	    <groupId>io.jsonwebtoken</groupId>
	    <artifactId>jjwt-impl</artifactId>
	    <version>0.12.7</version>
	    <scope>runtime</scope>
	</dependency>
	
	<!-- JSON Serializer -->
	<dependency>
	    <groupId>io.jsonwebtoken</groupId>
	    <artifactId>jjwt-jackson</artifactId>
	    <version>0.12.7</version>
	    <scope>runtime</scope>
	</dependency>
}

