package servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/service")
public class JpaTester {
	@PersistenceContext()
	EntityManager entityManager;

	@GET
	@Path("/ping.do")
	@Produces(MediaType.TEXT_PLAIN)
	public String ping() {
		return "PING-PING";
	}

	@GET
	@Path("/test.do")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() throws IOException {
		String result = new String();
		if (entityManager != null) {
			result = result.concat("### Created entity manager \n");
		} else {
			result = initManuallyEntityManager(result);
		}
		return result;
	}

	public String initManuallyEntityManager(String result) {
		result = result
				.concat("###Failed to create entity manager with ANNOTATION \n");

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("jpaMastering");
		entityManager = emf.createEntityManager();

		entityManager = emf.createEntityManager();
		if (entityManager != null) {
			result = result.concat("### Created Manually entity manager\n");
		} else {
			result = result
					.concat("###Failed to create Manually entity manager with \n");
		}
		return result;
	}
}
