import org.eclipse.rdf4j.query.*;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;

import java.util.Iterator;

public class DashboardManager {
    public static void main(String[] args) {
        String sparqlEndpoint = "https://agentlab.ru/rdf4j-server/repositories/iot_mov";
        Repository repo = new SPARQLRepository(sparqlEndpoint);
        repo.init();


        RepositoryConnection conn = repo.getConnection();

        String queryString = "select ?s ?p ?o where {?s ?p ?o}";
        TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
        TupleQueryResult result = tupleQuery.evaluate();

        while (result.hasNext()) {
            System.out.println("-------------------------------------------------------------");
            BindingSet bindingSet = result.next();
            Iterator<Binding> it = bindingSet.iterator();

            while (it.hasNext()) {
                Binding binding = it.next();
                System.out.printf("%s: %s\n", binding.getName(), binding.getValue().stringValue());
            }
        }
    }
}
