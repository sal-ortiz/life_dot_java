import Life.Field;

public class Life {


  public static void main( String[] args ) throws Exception {
    Field game_of_life = new Field( 80, 20 );
    game_of_life.randomize();
    int day = 0;

    do {
      System.out.print( "DAY: " + day + "\n" + 
                        game_of_life.inspect() + "\n" );

      game_of_life.process();
      game_of_life.update();

    } while( day++ < 10000 );
  }

}
