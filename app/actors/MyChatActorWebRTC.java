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




	public class MyChatActorWebRTC extends AbstractActor {

	  // static public Integer counter=0;
	   
	   static List <ActorRef> myMap=new ArrayList<ActorRef>();
		
		public static Props props(ActorRef out) {
	        return Props.create(MyChatActorWebRTC.class, out);
	    }

	    private final ActorRef out;

	   
	    
	    public  MyChatActorWebRTC(ActorRef out) {
	    	//counter=0;
	    	//counter=counter+1;
	    	Logger.debug("1_____MyChatActorWebRTCgetSelf().path()="+getSelf().path());
	    	
	    	Logger.debug("1_____MyChatActorWebRTCr +getSelf()="+getSelf());
	        this.out = out;
	      
	        
	      //  getSender().tell("test MCA", );
	        
	    }

	    @Override
	    public Receive createReceive() {
	    	//counter=counter++;
	    	//Logger.debug("createReceive + counter ="+counter);
	    	Logger.debug("2__________createReceive + ActorRef ="+this.out);
	    	
	    	ReceiveBuilder builder = ReceiveBuilder.create();
	    	
	    	myMap.add(out);
	    	
	    	Logger.debug("2__________myMap.size();="+myMap.size());
	    	Logger.debug("2__________myMap.get(0).path();="+myMap.get(0));
	    	builder.match(String.class, message -> {
	    		//Logger.debug(" Message="+ message +"  counter ="+counter);
	    		Logger.debug("3__________ builder.match --> Message="+message + "ActorRef ="+this.out);
	    		ActorSelection parentSelected = getContext().actorSelection(getContext().getParent().path());
	    		
	    		
	    		
	    		out.tell("{\"type\":\"GETROOM\",\"value\":" + "1" + "}", self());
	    		
	    			
	    		
	    	    });
	    	
	        return builder.build();
	    }
	    public void postStop() throws Exception {
	    	Logger.debug("5__________postStop -->out="+out);
	    	myMap.remove(out);
	    	
	    	self().tell(PoisonPill.getInstance(), self());
	    }
	   
	    
	 
	    
	


	
	

}
