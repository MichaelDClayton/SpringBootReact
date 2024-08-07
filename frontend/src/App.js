import {Component} from "react";

class App extends Component {
  state = {
    students: []
  };

  async componentDidMount() {
    const response = await fetch('/students');
    const body = await response.json();
    this.setState({students: body});
  }

  render() {
    const {students} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            {/*<img src={logo} className="App-logo" alt="logo" />*/}
            <div className="App-intro">
              <h2>Students</h2>
              {students.map(student =>
                  <div key={student.id}>
                    {student.firstName} {student.lastName} ({student.email})
                  </div>
              )}
            </div>
          </header>
        </div>
    );
  }
}
export default App;
