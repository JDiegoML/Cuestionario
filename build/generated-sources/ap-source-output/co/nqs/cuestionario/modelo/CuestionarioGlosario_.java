package co.nqs.cuestionario.modelo;

import co.nqs.cuestionario.modelo.Cuestionario;
import co.nqs.cuestionario.modelo.CuestionarioGlosarioPK;
import co.nqs.cuestionario.modelo.Glosario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-06T18:50:13")
@StaticMetamodel(CuestionarioGlosario.class)
public class CuestionarioGlosario_ { 

    public static volatile SingularAttribute<CuestionarioGlosario, Integer> tipoPregunta;
    public static volatile SingularAttribute<CuestionarioGlosario, CuestionarioGlosarioPK> cuestionarioGlosarioPK;
    public static volatile SingularAttribute<CuestionarioGlosario, Glosario> glosario;
    public static volatile SingularAttribute<CuestionarioGlosario, Cuestionario> cuestionario;

}