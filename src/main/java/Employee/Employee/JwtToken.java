package Employee.Employee;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken {

	private String JWTKEY = "auth";

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(SignatureAlgorithm.HS256, JWTKEY).compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(JWTKEY).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
