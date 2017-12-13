
/**
<p>This package contains runnable demos for the project (executable JAR files) -- See the full package description for the demos' operating directions.</p>

<p>Note that the JAR files are not listed in the Javadocs.  Please go to the actual 
<code>provided.demo</code> package in the codebase to find them.   
See the directions below on how to run the demos.</p>

<h3>MAKE SURE ALL THE REQUIRED PORTS ON YOUR COMPUTER ARE OPEN FIRST!!</h3>

<p>Note that the JAR files do NOT contain any code for any tasks.   All tasks are dynamically loaded, where 
the demos assume that the desired ITask implementation classes all have a public static field 
called <code>FACTORY</code> that is am ITaskFactory instance that will instantiate that ITask implementation.
</p>

<p>Some tasks in the provided code are pre-loaded for your convenience.   
The demos are capable of loading and executing student-written tasks as well
if the task class contains a public static FACTORY field as described above. </p>

<p>The demos should be capable of inter-operating with any student-written client or engine solutions 
and this can used for testing student solutions.</p>

<p>Student ITask implementations can be written separately from the student client or engine 
solutions and tested with the demo client and engine (Note that the demo client requires that 
an ITask be instantiatable by an ITaskFactory and that the ITaskFactory's make() method 
needs no more than a single String parameter.)</p>


<h3>How to Run the Demos:</h3>

<h4>Starting the demos:</h4>
<ul>
	<li>From inside of Eclipse:
		<ol>
			<li>Select the desired launch (run) configuration file in the "demo" folder.</li>
			<li>Click the green "Run" icon in Eclipse tool bar.</li>
		</ol>
	</li>
	<li>From a file browser:
		<ol>
			<li> Browser to either the "demo" folder under the "provided" folder under 
			either the "src" or "bin" folders.</li>
			<li> Double-click the desired JAR file ("engine_demo.jar" or 
			"client_demo.jar") to run it.</li>
		</ol>
	</li>
	<li> From the command line:
		<ol>
			<li>Navigate to either the "demo" folder under the "provided" folder 
			under either the "src" or "bin" folders.</li>
			<li>Run the following command:  java -jar jarname.jar    
   				where "jarname.jar" is either "engine_demo.jar" or "client_demo.jar"</li>
		</ol>
	</li>
</ul>
<p>Start a client and an engine on the same or different computers.   
Note that either client or engine could be a student solution.</p>


<p>Connecting the client to an engine:</p>
<ol>
	<li>On the client, enter the IP address of the engine computer 
	(displayed on the engine GUI).</li>
	<li>Click the "Connect" button.</li>
	<li>Both the client and the engine will display messages from 
	the other as well as connection status messages.</li>
</ol>


<p>Testing connectivity:</p>
<ul>
	<li>From the client to the engine:
		<ol>
			<li>On the client, type a message into the 
			"Send msg to remote host's view" text field and hit "Enter".</li>
			<li>The message should appear on the engine's GUI 
			plus be echoed on the client's GUI.</li>
		</ol>
	</li>
	<li>From the engine to the client:
		<ol>
			<li>On the client, type a message into the 
			"Send msg to remote client's view" text field and hit "Enter".</li>
			<li>The message should appear on the client's GUI plus be echoed 
			on the engine's GUI.</li>
		</ol>
	</li>
</ul>



<h3>Executing Tasks</h3>

<p>Adding new tasks:</p>
<ol>
	<li>On the client, type in the fully-qualified classname of a desired 
	ITask implementation. The demo client assumes that the desired ITask class 
	has a public static field called "FACTORY" that is the singleton instance 
	of the ITaskFactory for that ITask implementation.</li>
	<li>Click the "Add to lists" button and the ITaskFactory will appear on the 
	two drop-lists.</li>
</ol>

<p>Running a task:</p>
<ol>
	<li>Select the desired task to run from the top drop list.</li>
	<li>Type in an appropriate parameter for constructing the task.</li>
	<li>Click the "Run Task" button. </li>
	<li>Task results will appear on both the client and engine GUI's. 
	The engine will display the raw task results.
	The client will display the task results as formatted by the task's 
	ITaskResultFormatter object.</li>
</ol>
  
<p>Combine tasks:</p>
<ol>
	<li>Select the desired tasks from the top and bottom drop lists.</li>
	<li>Click the "Combine Tasks" button.   </li>
	<li>The combined task will appear on the drop lists. Combined tasks are 
	true binary composite tasks where the composite ("MultiTask") and the 
	composees are all transmitted to the engine for execution as a single task.
	When the MultiTask runs, it indicates when the individual composees are run.</li>  
</ol>    

<h3>Quitting the Demos</h3>
 
<p>Both the client and the engine have "Quit" buttons that will 
gracefully shut down their systems.</p>
 
 
 * @author Stephen Wong
 *
 */
package provided.demo;