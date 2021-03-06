package dev.game.gameobject;

import dev.game.Handler;
import dev.game.gameobject.moveableobject.Tank;

import java.awt.*;
import java.util.ArrayList;

public class ObjectManager {

    private Tank tank1;
    private Handler handler;
    private ScoreBoard sb;
    private ArrayList<GameObject> gameObjects;
    private int boxNum = 0;
    private int objNum = 0;

    public ObjectManager(Handler handler, Tank tank1){
        this.handler = handler;
        this.tank1 = tank1;
        sb = new ScoreBoard(handler, 64, 768, 200, 64);
        gameObjects = new ArrayList<GameObject>();
        addObject(tank1);
        addObject(sb);
    }

    public void tick(){



        for(int i = 0; i < gameObjects.size(); i++){
            GameObject o = gameObjects.get(i);
            o.tick();
            if(!o.isActive())
            {
                gameObjects.remove(o);
            }
        }
    }

    public void render(Graphics g){

        for(int i = 0; i < gameObjects.size(); i++){
            GameObject o = gameObjects.get(i);
            o.render(g);
        }
    }

    public Tank getTank1() {
        return tank1;
    }

    public void setTank1(Tank tank1) {
        this.tank1 = tank1;
    }

    public ScoreBoard getSb() { return sb; }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public int getObjNum() {
        return objNum;
    }

    public void setObjNum(int objNum) {
        this.objNum = objNum;
    }

    public int getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(int boxNum) {
        this.boxNum = boxNum;
    }

    public void addObject(GameObject o){
        gameObjects.add(o);
    }

    public void removeObject(GameObject o){

        gameObjects.remove(o);
    }

    public void printContents(){
        for(int i = 0; i < gameObjects.size(); i++)
        {
            System.out.println(gameObjects.get(i));
        }
    }
}
