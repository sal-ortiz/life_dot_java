
package Life;

public class Cell {

  public enum CellState {  DEAD_TODAY_DEAD_TOMORROW, 
                           LIVE_TODAY_DEAD_TOMORROW, 
                           DEAD_TODAY_LIVE_TOMORROW, 
                           LIVE_TODAY_LIVE_TOMORROW };

  private CellState state;

  public Cell(){
    setState( CellState.DEAD_TODAY_DEAD_TOMORROW );
  }

  public Cell( int newState ){
    setState( newState );
  }

  public Cell( CellState newState ){
    setState( newState );
  }

  public CellState state(){
    return state;
  }

  public Boolean isAliveToday(){
    return ( (state.ordinal() % 2) == 1 );
  }
  public Boolean isDeadToday(){
    return ( (state.ordinal() % 2) == 0 );
  }
  public Boolean isAliveTomorrow(){
    return ( (state.ordinal() / 2) == 1 );
  }
  public Boolean isDeadTomorrow(){
    return ( (state.ordinal() / 2) == 0 );
  }

  public CellState setState( int newState ){
    state = CellState.values()[ newState ];

    return state;
  }

  public CellState setState( CellState newState ){
    state = newState;
    return state;
  }

  public CellState liveToday(){
    if( (state.ordinal() / 2) == 0 )
      return setState( CellState.LIVE_TODAY_DEAD_TOMORROW );
    else
      return setState( CellState.LIVE_TODAY_LIVE_TOMORROW );
  }

  public CellState deadToday(){
    if( (state.ordinal() / 2) == 0 )
      return setState( CellState.DEAD_TODAY_DEAD_TOMORROW );
    else
      return setState( CellState.DEAD_TODAY_LIVE_TOMORROW );
  }

  public CellState liveTomorrow(){
    if( (state.ordinal() % 2) == 0 )
      return setState( CellState.DEAD_TODAY_LIVE_TOMORROW );
    else
      return setState( CellState.LIVE_TODAY_LIVE_TOMORROW );
  }

  public CellState deadTomorrow(){
    if( (state.ordinal() % 2) == 0 )
      return setState( CellState.DEAD_TODAY_DEAD_TOMORROW );
    else
      return setState( CellState.LIVE_TODAY_DEAD_TOMORROW );
  }


} // class Cell
