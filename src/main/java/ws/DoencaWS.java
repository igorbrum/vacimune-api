package ws;

import entity.Doenca;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rn.DoencaRN;

/**
 * REST Web Service
 *
 * @author 631610042
 */
@Path("doenca")
public class DoencaWS {

    DoencaRN doencaRN;
    //private static int autoIncr = 1;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DoencaWS
     */
    public DoencaWS() {
        doencaRN = new DoencaRN();
        if(doencaRN.listar().isEmpty()){
            doencaRN.inserir(new Doenca(Long.valueOf(1),"Caxumba"));
            doencaRN.inserir(new Doenca(Long.valueOf(2),"Difteria"));
            doencaRN.inserir(new Doenca(Long.valueOf(3),"Febre Amarela"));
            doencaRN.inserir(new Doenca(Long.valueOf(4),"Gripe"));
            doencaRN.inserir(new Doenca(Long.valueOf(5),"Hepatite A")); // ainda nao colocado no watson...
            doencaRN.inserir(new Doenca(Long.valueOf(6),"Hepatite B"));
            doencaRN.inserir(new Doenca(Long.valueOf(7),"HPV"));
            doencaRN.inserir(new Doenca(Long.valueOf(8),"Meningite"));
            doencaRN.inserir(new Doenca(Long.valueOf(9),"Pertussis (Coqueluche)"));
            doencaRN.inserir(new Doenca(Long.valueOf(10),"Pneumonia"));
            doencaRN.inserir(new Doenca(Long.valueOf(11),"Poliomielite"));
            doencaRN.inserir(new Doenca(Long.valueOf(12),"Rotavirus"));
            doencaRN.inserir(new Doenca(Long.valueOf(13),"Rubeola"));
            doencaRN.inserir(new Doenca(Long.valueOf(14),"Sarampo"));
            doencaRN.inserir(new Doenca(Long.valueOf(15),"Tetano"));
            doencaRN.inserir(new Doenca(Long.valueOf(16),"Tuberculose (BCG)"));
        }
    }

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Doenca> getDoencas() {
        return (doencaRN.listar());

    }*/
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoencas() {
        List<Doenca> doenca = doencaRN.listar();
        
        return Response.ok()
                .entity(doenca)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET")
                .allow("OPTIONS").build();
    }

    /*@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Doenca adicionar(Doenca doenca,
            @Context HttpServletResponse response) {

        doencaRN.inserir(doenca);

        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new javax.ws.rs.InternalServerErrorException();
        }
        return doenca;
    }*/
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionar(Doenca doenca, @Context HttpServletResponse response) {

        doencaRN.inserir(doenca);
        response.setStatus(HttpServletResponse.SC_CREATED);
        
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new javax.ws.rs.InternalServerErrorException();
        }
        
        return Response.ok()
                .entity(doenca)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST")
                .allow("OPTIONS").build();
    }

    /*@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Doenca getDoencaPorId(@PathParam("id") Long id) {
        return doencaRN.buscarPorId(id);
    }*/
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoencaPorId(@PathParam("id") Long id) {
        Doenca doenca = doencaRN.buscarPorId(id);
        return Response.ok()
                .entity(doenca)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET")
                .allow("OPTIONS").build();
    }

    /*@PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Doenca atualizar(@PathParam("id") Long id,
            Doenca doenca){
        doenca.setId(id);
        Doenca doencaAtualizado = doencaRN.atualizar(doenca);
        return doencaAtualizado;
    }*/
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") Long id, Doenca doenca){
        doenca.setId(id);
        Doenca doencaAtualizado = doencaRN.atualizar(doenca);
        return Response.ok()
                .entity(doencaAtualizado)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "PUT")
                .allow("OPTIONS").build();
    }
    
    /*@DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Doenca deletar(@PathParam("id") Long id){
        Doenca doencaDeletado = doencaRN.deletar(id);
        return doencaDeletado;
    }*/
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Long id){
        Doenca doencaDeletado = doencaRN.deletar(id);
        return Response.ok()
                .entity(doencaDeletado)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "DELETE")
                .allow("OPTIONS").build();
    }
}