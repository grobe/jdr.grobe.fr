package actors;

import akka.actor.*;
import akka.actor.AbstractActor.Receive;

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
        return receiveBuilder()
          .match(String.class, message ->
              out.tell("I received your messagsssssssssse: " + message, self())
            )
          .build();
    }
 /*   public void postStop() throws Exception {
    	//self().tell(PoisonPill.getInstance(), self());
    }
   */ 
}
