package actors;

import java.util.List;
import java.util.stream.Collectors;

import akka.actor.*;
import akka.actor.AbstractActor.Receive;

import play.Logger;

public class MyWebSocketActor extends AbstractActor {

    public static Props props(ActorRef out) {
        return Props.create(MyWebSocketActor.class, out);
    }

    private final ActorRef out;

    public MyWebSocketActor(ActorRef out) {
        this.out = out;
    }

    @Override
    public Receive createReceive() {
    	Logger.debug("createReceive");
    	
        return receiveBuilder().match(String.class, message -> {Logger.debug(" Message="+message);
        	                                                    out.tell("I received your messagsssssssssse: " + message, self());
        	                                                    }).build();
    }
 /*   public void postStop() throws Exception {
    	//self().tell(PoisonPill.getInstance(), self());
    }
   */ 
}

