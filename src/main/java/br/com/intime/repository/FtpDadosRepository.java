package br.com.intime.repository;
 
import br.com.intime.model.Ftpdados;
import javax.ejb.Stateless;

@Stateless
public class FtpDadosRepository extends AbstractRepository<Ftpdados> {
    
    public FtpDadosRepository() {
        super(Ftpdados.class);
    }
}
