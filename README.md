## Tom (Task Manager)

> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity/your-mind-is-for-having-ideas-not-holding-them/))

**Tom** is a lightweight, *command-line* task manager that helps you track todos, deadlines, and events without the overhead of a full GUI app.

### Why Tom?
- **Fast** to use with typed commands
- Simple, readable output
- Keeps your tasks in one place  
- ~~Forgets~~ *remembers* things so you don’t have to

### Quick start
1. Download the latest `.jar` from your releases page.
2. Run it with `java -jar tom.jar`
3. Add tasks and manage them from the terminal.

### Example commands
```java
// Add tasks
todo read book
deadline submit iP /by 2026-02-07
event tutorial /from 2026-02-05 /to 2026-02-05

// Find and manage
find book
list
mark 1
unmark 1
delete 1
