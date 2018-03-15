package com.folkscube.foots.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.folkscube.foots.GameFoot;
import com.folkscube.foots.basic.BoxManager;
import com.folkscube.foots.basic.Conv;
import com.folkscube.foots.basic.Grid;
import com.folkscube.foots.basic.SavedObject;
import com.folkscube.foots.controllers.MainController;
import com.folkscube.foots.managers.ShaderManager;
import com.folkscube.foots.managers.TextureManager;
import com.folkscube.foots.player.Player;
import com.folkscube.foots.renderers.BodyRenderer;
import com.folkscube.foots.renderers.BoxRenderer;



public class GameScreen implements Screen{
	
	public static final float PPM 	=200f;
	public static final float OBW	=20f/PPM;
	public static final float BW	=20f;
	
	GameFoot game				=null;
	Stage stage					=null;
	World world					=null;
	Player player				=null;
	OrthographicCamera camera	=null;
	OrthographicCamera another	=null;
	Box2DDebugRenderer renderer	=null;
	ShapeRenderer grenderer		=null;
	BodyRenderer brenderer		=null;
	SpriteBatch batch			=null;
	
	
	//circle
	BodyDef bodyDef				=null;
	FixtureDef fixtureDef		=null;
	Body body					=null;
	//boxes
	BodyDef bxdef				=null;
	FixtureDef fxdef			=null;
	
	Grid grid					=null;
	
	MainController controller	=null;
	
	//shader outline
	ShaderProgram shaderOutline	=null;
	
	public GameScreen(GameFoot game){
		this.game=game;
		SavedObject.game_screen=this;
	}
	
	@Override
	public void show() {
		ShaderManager.manager.SHAPE_OUTLINE=ShaderManager.manager.loadShader("vertex_shader.glsl","fragment_shader.glsl");
		TextureManager.manager.loadTexture("pb1","metal_block.png");
		
		if(camera==null){					//primary camera for game display
			camera=new OrthographicCamera();
			camera.setToOrtho(false, Gdx.graphics.getWidth()/PPM, Gdx.graphics.getHeight()/PPM);
			camera.position.x=0;
			camera.position.y=0;
		}
		SavedObject.world_camera	=	camera;
		if(another==null){					//secondary camera for HUD and command display
			another=new OrthographicCamera();
			another.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			another.position.x=0;
			another.position.y=0;
		}
		//initialize the grid, batch, box2drenderer and shapeRenderer
		grid		=	new Grid(0,0,(int)((Gdx.graphics.getWidth())/BW),(int)((Gdx.graphics.getHeight())/BW),OBW,OBW,new Color(0,0.1f,0,0.1f));
		batch		=	new SpriteBatch();
		renderer	=	new Box2DDebugRenderer();
		grenderer	=	new ShapeRenderer();
		brenderer	=	BodyRenderer.renderer;
		//initialize the world and body definition objects
		world		=	new World(new Vector2(0,-9.81f),true);
		SavedObject.world=world;
		stage		=	new Stage();
		bodyDef		=	new BodyDef();
		fixtureDef	=	new FixtureDef();
		
		/*************************PLAYER******************************/
		//body definition
		bodyDef.type		=	BodyType.DynamicBody;
		bodyDef.position.x	=	Conv.getcx((Gdx.graphics.getWidth()/2)/PPM,10f/PPM);
		bodyDef.position.y	=	Conv.getcy((Gdx.graphics.getHeight()/2)/PPM,10f/PPM);
		
		body		=	world.createBody(bodyDef);
		
		//create a circle shape
		CircleShape circle	=	new CircleShape();
		circle.setRadius(10f/PPM);
		
		//fixture definition
		fixtureDef.density	=	100f;
		fixtureDef.friction	=	10f;
		fixtureDef.shape	=	circle;
		fixtureDef.friction	=	1.0f;
		
		//create the fixture
		body.createFixture(fixtureDef);
		player				=	new Player(body);
		player.setLaserColor(Color.RED);
		SavedObject.player	=	player;
		/*************************BOX********************************/
		bxdef		=	new BodyDef();
		fxdef		=	new FixtureDef();
		
		bxdef.type			=	BodyType.StaticBody;
		bxdef.position.x	=	Conv.getrx(200/PPM,OBW);
		bxdef.position.y	=	Conv.getry(200/PPM,OBW);
		
		SavedObject.bxdef=bxdef;
		
		
		
		PolygonShape bxshape=new PolygonShape();
		bxshape.setAsBox(OBW/2, OBW/2);
		
		fxdef.density		=	100f;
		fxdef.friction		=	1.0f;
		fxdef.shape			=	bxshape;
		SavedObject.fxdef=fxdef;

		/*************************GROUND*****************************/
		//body definition
		bodyDef.type		=	BodyType.StaticBody;
		bodyDef.position.x	=	Conv.getrx((Gdx.graphics.getWidth()/2)/PPM,80f/PPM);
		bodyDef.position.y	=	Conv.getry(100/PPM,20f/PPM);
		
		Body ground			=	world.createBody(bodyDef);
		//fixture definition
		PolygonShape shape	=	new PolygonShape();
		shape.setAsBox(40f/PPM, 10f/PPM);
		fixtureDef.shape	=	shape;
		fixtureDef.friction	=	1.0f;
		ground.createFixture(fixtureDef);
		//finalizing with initialization of controller
		controller=new MainController();
	}

	@Override
	public void render(float delta) {
		world.step(1/60f, 6, 2);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Shape Renderer
		grenderer.begin(ShapeType.Line);
		grenderer.setProjectionMatrix(camera.combined);
		
			this.grid.drawGrid(grenderer);
		    BoxRenderer.renderer.render(grenderer);
		    player.render(grenderer);

		grenderer.end();
		//TextureRenderer
		batch.setProjectionMatrix(camera.combined);
			brenderer.render(batch);
		//Box2DDebugRenderer
		renderer.render(world, camera.combined);
		
		//drawing from batch with another camera projection
		batch.setProjectionMatrix(another.combined);
		batch.begin();
			controller.render(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		unloadShader();
	}
	
	public void unloadShader(){
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		world.dispose();
		stage.dispose();
		renderer.dispose();
		batch.dispose();
		BoxManager.manager.dispose();
		controller.dispose();
		controller=null;
	}

}
