
package Life;

import java.awt.Point;
import java.util.Random;
import Life.Cell;


public class Field {
  public Cell[] data;
  public int width;
  public int height;

  public Field( int fieldWidth, int fieldHeight ){
    width = fieldWidth;
    height = fieldHeight;

    data = new Cell[ fieldWidth*fieldHeight ];
    for( int index = 0; index < area(); index++ )
      data[ index ] = new Cell();
  }

  public Cell cell( int xPos, int yPos ){
    return data[ coordsToIndex( xPos, yPos ) ];
  }

  public Cell cell( int index ){
    return data[ index ];
  }

  public int area(){
    return width * height;
  }

  public void randomize(){
    Random rand = new Random();
    for( int index = 0; index < area(); index++ ){
      if( rand.nextInt(2) > 0 )
        data[ index ].liveToday();
      else
        data[ index ].deadToday();
    }
  }

  public String inspect(){
    return formatForDisplay( '1', '0' );
  }

  public String inspect( char liveChar, char deadChar ){
    return formatForDisplay( liveChar, deadChar );
  }

  public int numNeighbors( int centerIndex ){
    int numNeighbors = 0;
    int index = 0;
    for( int posModifier=0; posModifier<9; posModifier++ ){
      // x coordinate.
      index = (centerIndex%width) + ( (posModifier % 3) - 1 );

      // y coordinate.
      index += ( (centerIndex / width) + ( (posModifier / 3) - 1 ) ) * width;

      if( ( index != centerIndex ) &&
          ( index > 0 ) && ( index < area() ) &&  // this is wrong...we should wrap.
          data[index].isAliveToday() )    numNeighbors++;
    }

    return numNeighbors;
  }

  public int numNeighbors( int xPos, int yPos ){
    return numNeighbors( coordsToIndex(xPos,yPos) );
  }

  public void process(){
    int neighbCount;
    for( int index=0; index < area(); index++ ){
      neighbCount = numNeighbors( index );
      if( data[index].isAliveToday() ){
        // our cell is currently alive.
        if( (neighbCount>=2) && (neighbCount<=3) )
          data[index].liveTomorrow();
        else
          data[index].deadTomorrow();
      } else {
        // our cell is currently dead.
        if( neighbCount==3 )
          data[index].liveTomorrow();
        else
          data[index].deadTomorrow();
      }
    }
  }

  public void update(){
    int newState;
    for( int index=0; index < area(); index++ ){
      if( (data[ index ].state().ordinal() / 2) == 1 )
        data[ index ].liveToday();
      else
        data[ index ].deadToday();

      data[ index ].deadTomorrow();
    }
  }

  private int coordsToIndex( int xPos, int yPos ){
    return ( yPos * width ) + yPos;
  }

  private Point indexToCoords( int index ){
    return new Point(
      ( index % width ),  // x coordinate.
      ( index / width )   // y coordinate.
    );
  }

  private String formatForDisplay( char liveChar, char deadChar ){
    String fieldOutput = new String("");
    for( int index=0; index < area(); index++ ){
      if( (index > 0) && (index % width == 0) )
        fieldOutput += "\n";

      if( data[index].isAliveToday() )  // <-- null pointer exception HERE!
        fieldOutput += liveChar;
      else
        fieldOutput += deadChar;

    }
    return fieldOutput;
  }

} // class Field
