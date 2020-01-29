import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import LinearProgress from '@material-ui/core/LinearProgress';
import Typography from '@material-ui/core/Typography';
import {  Redirect } from 'react-router-dom';
const useStyles = makeStyles({
  root: {
    flexGrow: 3,
    align: 'center',
    justifyContent: 'center',
  },
});

export default function Transicion(props) {
    
  
  const classes = useStyles();
  const [completed, setCompleted] = React.useState(0);

  React.useEffect(() => {
    function progress() {
      setCompleted(oldCompleted => {
        if (oldCompleted === 100) {
          return 0;
        }
        const diff = Math.random() * 10;
        return Math.min(oldCompleted + diff, 100);
      });
    }

    const timer = setInterval(progress, 150);
    return () => {
      clearInterval(timer);
    };
  }, []);

  if (completed === 100){
      if(props.location.state==null) {      
          return <Redirect to={{
              pathname: '/reserva'        
          }} />
      }else{
           return <Redirect to={{
              pathname: '/cliente',
              state: { data:props.location.state.data}
          }} />
      }
  }
  return (
    
    <div className={classes.root}>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <LinearProgress variant="determinate" value={completed} />
        <br/>
        <Typography variant="h5" component="h3">
            
        </Typography>
        <br />
        <LinearProgress color="secondary" variant="determinate" value={completed} />
    </div>
  );
}