package co.nqs.cuestionario.modelo;

import co.nqs.cuestionario.modelo.Cuestionario;
import co.nqs.cuestionario.modelo.Curso;
import co.nqs.cuestionario.modelo.Glosario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-06T18:50:13")
@StaticMetamodel(Tema.class)
public class Tema_ { 

    public static volatile SingularAttribute<Tema, String> descripcion;
    public static volatile SingularAttribute<Tema, Integer> temaId;
    public static volatile ListAttribute<Tema, Cuestionario> cuestionarioList;
    public static volatile SingularAttribute<Tema, Curso> cursoId;
    public static volatile SingularAttribute<Tema, String> nombre;
    public static volatile ListAttribute<Tema, Glosario> glosarioList;

}