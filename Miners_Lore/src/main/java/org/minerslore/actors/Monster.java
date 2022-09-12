package org.minerslore.actors;

import org.minerslore.mapitems.Item;

import java.awt.*;

public class Monster extends Actor {

    private static final char SYMBOL = 0x00A5;
    private static final String MESSAGE = "Gold stealing creature of lore.";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public Monster(char symbol, Point location) {
        super(SYMBOL, location);
    }

    public char move(Miner miner,int colSize,int rowSize) {
        int xDist=Math.abs(miner.getX()-this.getX());
        int yDist=Math.abs(miner.getY()-this.getY());
        System.out.println("Test me");
        System.out.println(yDist);
        System.out.println(xDist);
        if(xDist+yDist==1){


            return ' ';
        }


        if (this.getX()<miner.getX()){
            if(xDist<rowSize/2 && ((Item)this.getE()).isPath()){
                return 'D';
            } else  if(Math.abs(miner.getX()-this.getX())>rowSize/2 && ((Item)this.getE()).isPath()){
                return 'A';
            }
        } if (this.getX()>miner.getX()){
            if(xDist>rowSize/2 && ((Item)this.getE()).isPath()){
                return 'D';
            } else  if(xDist<rowSize/2 && ((Item)this.getE()).isPath()){
                return 'A';
            }
        }
        if (this.getY()<miner.getY()){
            if(yDist<rowSize/2 && ((Item)this.getS()).isPath()){
                return 'S';
            } else  if(Math.abs(miner.getY()-this.getY())>rowSize/2 && ((Item)this.getN()).isPath()){
                return 'W';
            }
        } if (this.getY()>miner.getY()){
            if(yDist>rowSize/2 && ((Item)this.getS()).isPath()){
                return 'S';
            } else  if(yDist<rowSize/2 && ((Item)this.getN()).isPath()){
                return 'N';
            }
        }


        return ' ';
    }

    @Override
    public void encounter() {
        System.out.println("Monster Fight");

    }

    @Override
    public String toString() {
        return TEXT_RED  + super.toString() + ANSI_RESET;
    }
}
