package com.phalaenopsis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.phalaenopsis.model.Play;
import com.phalaenopsis.model.Scene;
import com.phalaenopsis.repository.LineRepository;
import com.phalaenopsis.repository.PlayRepository;
import com.phalaenopsis.repository.SceneRepository;
import com.phalaenopsis.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhalaenopsisApplicationTests {

	@Autowired
    UserRepository repository;

    @Autowired
    PlayRepository playRepository;
    
    @Autowired
    SceneRepository sceneRepository;
    
    @Autowired
    LineRepository lineRepository;
    
	@Test
	public void contextLoads() {
		/*
		Play play = new Play();
    	play.setName("Play");
    	Scene scene1 = new Scene();
    	scene1.setName("Scene1");
    	scene1.setOrdinal(1l);
    	Scene scene2 = new Scene();
    	scene2.setName("Scene2");
    	scene2.setOrdinal(2l);
    	Line line1 = new Line();
    	line1.setOrdinal(1l);
    	Line line2 = new Line();
    	line2.setOrdinal(2l);
    	Line line3 = new Line();
    	line3.setOrdinal(1l);
    	Line line4 = new Line();
    	line4.setOrdinal(2l);
    	    
    	
    	List<Scene> arrScene = new ArrayList<Scene>();
    	arrScene.add(scene1);
    	arrScene.add(scene2);
    	
    	List<Line> arrLine1 = new ArrayList<Line>();
    	arrLine1.add(line1);
    	arrLine1.add(line2);
    	
    	List<Line> arrLine2 = new ArrayList<Line>();
    	arrLine1.add(line3);
    	arrLine1.add(line4);
    	
    	line1.setScene(scene1);
    	line2.setScene(scene1);
    	
    	line3.setScene(scene2);
    	line4.setScene(scene2);
    	
    	scene1.setLines(arrLine1);
    	scene2.setLines(arrLine2);
    	
    	scene1.setPlay(play);
    	scene2.setPlay(play);
    	play.setScenes(arrScene);
    	playRepository.save(play);
    	System.out.println("Now begin to delete");
    	// playRepository.delete(play);
    	*/
    	
	}
	
	@Test
	public void addNewScene() {
		
		Scene scene3 = new Scene();
		Long count = sceneRepository.count()+1;
    	scene3.setName("Scene" + count);
    	scene3.setOrdinal(count);
    	
    	Play play = playRepository.findOne(2l);
    	scene3.setPlay(play);
    	sceneRepository.save(scene3);
    	
	}
	
	@Test
	public void updateNewScene() {
		
		Scene scene = sceneRepository.findOne(5l);
		Long count = sceneRepository.count()+1;
    	scene.setName("Scene" + count);
    	sceneRepository.save(scene);
    	
	}

}
