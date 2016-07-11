package co.nqs.cuestionario.modelo;

import co.nqs.cuestionario.modelo.CuestionarioGlosario;
import co.nqs.cuestionario.modelo.Tema;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-06T18:50:13")
@StaticMetamodel(Glosario.class)
public class Glosario_ { 

    public static volatile SingularAttribute<Glosario, String> descripcion;
    public static volatile SingularAttribute<Glosario, Integer> glosarioId;
    public static volatile SingularAttribute<Glosario, Tema> temaId;
    public static volatile ListAttribute<Glosario, CuestionarioGlosario> cuestionarioGlosarioList;
    public static volatile SingularAttribute<Glosario, String> nombre;

}