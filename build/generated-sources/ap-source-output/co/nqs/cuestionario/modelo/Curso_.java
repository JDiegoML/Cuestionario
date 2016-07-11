package co.nqs.cuestionario.modelo;

import co.nqs.cuestionario.modelo.Tema;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-06T18:50:13")
@StaticMetamodel(Curso.class)
public class Curso_ { 

    public static volatile SingularAttribute<Curso, Integer> cursoId;
    public static volatile ListAttribute<Curso, Tema> temaList;
    public static volatile SingularAttribute<Curso, String> nombre;
    public static volatile SingularAttribute<Curso, Integer> cantidadEstudiantes;
    public static volatile SingularAttribute<Curso, String> salon;

}