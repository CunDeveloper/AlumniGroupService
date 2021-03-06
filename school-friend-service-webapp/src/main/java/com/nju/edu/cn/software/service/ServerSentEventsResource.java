package com.nju.edu.cn.software.service;

import java.io.IOException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

@Path("server-sent-events")
public class ServerSentEventsResource {

    private static EventOutput eventOutput = new EventOutput();

    @GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput getMessageQueue() {
        return eventOutput;
    }

    @POST
    public void addMessage(final String message) throws IOException {
        eventOutput.write(new OutboundEvent.Builder().name("custom-message").data(String.class, message).build());
    }

    @DELETE
    public void close() throws IOException {
        eventOutput.close();
        ServerSentEventsResource.setEventOutput(new EventOutput());
    }

    @POST
    @Path("domains/{id}")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput startDomain(@PathParam("id") final String id) {
        final EventOutput seq = new EventOutput();

        new Thread() {
            public void run() {
                try {
                    seq.write(new OutboundEvent.Builder().name("domain-progress")
                            .data(String.class, "starting domain " + id + " ...").build());
                    Thread.sleep(200);
                    seq.write(new OutboundEvent.Builder().name("domain-progress").data(String.class, "50%").build());
                    Thread.sleep(200);
                    seq.write(new OutboundEvent.Builder().name("domain-progress").data(String.class, "60%").build());
                    Thread.sleep(200);
                    seq.write(new OutboundEvent.Builder().name("domain-progress").data(String.class, "70%").build());
                    Thread.sleep(200);
                    seq.write(new OutboundEvent.Builder().name("domain-progress").data(String.class, "99%").build());
                    Thread.sleep(200);
                    seq.write(new OutboundEvent.Builder().name("domain-progress").data(String.class, "done").build());
                    seq.close();

                } catch (final InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        return seq;
    }

    private static void setEventOutput(final EventOutput eventOutput) {
        ServerSentEventsResource.eventOutput = eventOutput;
    }
}