package actors;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import akka.actor.*;
import akka.actor.AbstractActor.Receive;
import akka.japi.pf.ReceiveBuilder;
import play.Logger;


public class MyWebSocketActor extends AbstractActor {

   static public Integer counter=0;
	
	public static Props props(ActorRef out) {
        return Props.create(MyWebSocketActor.class, out);
    }

    private final ActorRef out;


    
    public  MyWebSocketActor(ActorRef out) {
    	//counter=0;
    	counter=counter+1;
    	Logger.debug("MyWebSocketActor + counter ="+counter+ " getSelf().path() "+getSelf().path());
        this.out = out;
      //  getSender().tell("test MCA", );
        
    }

    @Override
    public Receive createReceive() {
    	counter=counter++;
    	Logger.debug("createReceive + counter ="+counter);
    	
    	ReceiveBuilder builder = ReceiveBuilder.create();
    	
    	
    	
    	builder.match(String.class, message -> {
    		Logger.debug(" Message="+message +" counter ="+counter);
            out.tell("I received your messagsssssssssse: " + message + " counter ="+counter, self());
    	    });
    	
        return builder.build();
    }
    public void postStop() throws Exception {
    	Logger.debug("____________postStop + counter ="+counter);
    	//self().tell(PoisonPill.getInstance(), self());
    }
   
    
 
    
}

