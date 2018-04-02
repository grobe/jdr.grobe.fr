package controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

import akka.actor.ActorSystem;
import akka.stream.Materializer;
import play.mvc.*;

public class WebsocketController extends Controller {

	 static public  int toto;
		private final ActorSystem actorSystem;
	    private final Materializer materializer;

	@Singleton
	@Inject
	    public WebsocketController(ActorSystem actorSystem, Materializer materializer) {
	        this.actorSystem = actorSystem;
	        this.materializer = materializer;
	    }






}
