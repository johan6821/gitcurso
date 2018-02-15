/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.mil.ejercito.ws.service;

import co.mil.ejercito.ws.JobHistory;
import co.mil.ejercito.ws.JobHistoryPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author Administrator
 */
@Stateless
@Path("co.mil.ejercito.ws.jobhistory")
public class JobHistoryFacadeREST extends AbstractFacade<JobHistory> {
    @PersistenceContext(unitName = "ConsultashrPU")
    private EntityManager em;

    private JobHistoryPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;employeeId=employeeIdValue;startDate=startDateValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        co.mil.ejercito.ws.JobHistoryPK key = new co.mil.ejercito.ws.JobHistoryPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> employeeId = map.get("employeeId");
        if (employeeId != null && !employeeId.isEmpty()) {
            key.setEmployeeId(new java.lang.Integer(employeeId.get(0)));
        }
        java.util.List<String> startDate = map.get("startDate");
        if (startDate != null && !startDate.isEmpty()) {
            key.setStartDate(new java.util.Date(startDate.get(0)));
        }
        return key;
    }

    public JobHistoryFacadeREST() {
        super(JobHistory.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(JobHistory entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, JobHistory entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        co.mil.ejercito.ws.JobHistoryPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public JobHistory find(@PathParam("id") PathSegment id) {
        co.mil.ejercito.ws.JobHistoryPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<JobHistory> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<JobHistory> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
