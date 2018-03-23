package actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import akka.actor.*;
import akka.actor.AbstractActor.Receive;
import akka.japi.pf.ReceiveBuilder;
import play.Logger;


public class MyWebSocketActor extends AbstractActor {

   static public Integer counter=0;
   
   static List <ActorRef> myMap=new ArrayList<ActorRef>();
	
	public static Props props(ActorRef out) {
        return Props.create(MyWebSocketActor.class, out);
    }

    private final ActorRef out;

   
    
    public  MyWebSocketActor(ActorRef out) {
    	//counter=0;
    	counter=counter+1;
    	Logger.debug("MyWebSocketActor + counter ="+counter+ " getSelf().path()="+getSelf().path());
    	
    	Logger.debug("MyWebSocketActor +getSelf()="+getSelf());
        this.out = out;
      
        
      //  getSender().tell("test MCA", );
        
    }

    @Override
    public Receive createReceive() {
    	counter=counter++;
    	Logger.debug("createReceive + counter ="+counter);
    	Logger.debug("createReceive + ActorRef ="+this.out);
    	
    	ReceiveBuilder builder = ReceiveBuilder.create();
    	
    	myMap.add(out);
    	
    	Logger.debug("myMap.size();="+myMap.size());
    	Logger.debug("myMap.get(0).path();="+myMap.get(0));
    	builder.match(String.class, message -> {
    		Logger.debug(" Message="+ message +"  counter ="+counter);
    		Logger.debug(" Message="+message + "ActorRef ="+this.out);
    		ActorSelection selection = getContext().actorSelection(getContext().getParent().path());
    		
    		
    		
    		
    		Logger.debug("selection.pathString()="+selection.pathString());
    		
    		myMap.forEach(msg->{
    			          Logger.debug("-"+out+" msg ="+msg);
    					  msg.tell("-"+sender()+": " + message + " counter ="+counter, self());
    		
    						});
    		//out.tell("I received your messagsssssssssse: " + message + " counter ="+counter, self());
    		
    		
    	    });
    	
        return builder.build();
    }
    public void postStop() throws Exception {
    	Logger.debug("____________postStop + counter ="+counter);
    	myMap.remove(out);
    	counter=counter-1;
    	self().tell(PoisonPill.getInstance(), self());
    }
   
    
 
    
}

