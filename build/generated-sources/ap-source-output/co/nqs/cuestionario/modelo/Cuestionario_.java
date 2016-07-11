package co.nqs.cuestionario.modelo;

import co.nqs.cuestionario.modelo.CuestionarioGlosario;
import co.nqs.cuestionario.modelo.Tema;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-06T18:50:13")
@StaticMetamodel(Cuestionario.class)
public class Cuestionario_ { 

    public static volatile SingularAttribute<Cuestionario, Tema> temaId;
    public static volatile SingularAttribute<Cuestionario, Integer> cuestionarioId;
    public static volatile SingularAttribute<Cuestionario, Integer> cantidadAbierta;
    public static volatile SingularAttribute<Cuestionario, Integer> cantidadFalsoVerdadero;
    public static volatile ListAttribute<Cuestionario, CuestionarioGlosario> cuestionarioGlosarioList;
    public static volatile SingularAttribute<Cuestionario, Integer> cantidadMultiple;

}