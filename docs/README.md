# Tom User Guide

Tom is a lightweight task manager that lets you capture todos, deadlines, and events from a simple command-driven UI. It is built for speed: type a command, hit enter, and Tom responds immediately with the updated task state.

## Quick Start

1. Ensure you have Java 17 (or later) installed.
2. Run the app:

```bash
java -jar tom.jar
```

If your jar has a different name, replace `tom.jar` with the actual filename.
3. Type commands into the input box and press Enter or click Send.

## Command Format

- Commands are case-insensitive for the first word (e.g., `list`, `List`, `LIST`).
- Task numbers are 1-based and come from the `list` command.
- Dates must be in ISO format: `yyyy-MM-dd`.
- Text in `UPPER_SNAKE_CASE` below represents user-supplied values.

## Features

### List Tasks

Shows all tasks in your list.

Command:
`list`

Example output:
```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] submit report (by: Feb 18 2026)
```

### Add a Todo

Creates a basic task with a description.

Command:
`todo DESCRIPTION`

Example:
`todo read book`

Expected response:
```
Got it. I've added this task:
Now you have 1 tasks in the list.
```

### Add a Deadline

Creates a task with a due date.

Command:
`deadline DESCRIPTION /by YYYY-MM-DD`

Example:
`deadline submit report /by 2026-02-18`

Example output format:
```
[D][ ] submit report (by: Feb 18 2026)
```

### Add an Event

Creates a task with a start and end date.

Command:
`event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`

Example:
`event conference /from 2026-03-01 /to 2026-03-03`

Example output format:
```
[E][ ] conference (from: Mar 01 2026 to: Mar 03 2026)
```

### Mark a Task as Done

Marks a task as completed.

Command:
`mark TASK_NUMBER`

Example:
`mark 2`

Expected response:
```
Nice! I've marked this task as done:
  [D][X] submit report (by: Feb 18 2026)
```

### Unmark a Task

Marks a task as not done.

Command:
`unmark TASK_NUMBER`

Example:
`unmark 2`

Expected response:
```
Nice! I've marked this task as undone:
  [D][ ] submit report (by: Feb 18 2026)
```

### Delete a Task

Removes a task from the list.

Command:
`delete TASK_NUMBER`

Example:
`delete 1`

Expected response:
```
Noted. I've removed this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

### Find Tasks

Filters tasks that contain a keyword (case-insensitive).

Command:
`find KEYWORD`

Example:
`find report`

Expected response:
```
Found 1 matching tasks in your list:
Here are the tasks matching "report":
  [D][ ] submit report (by: Feb 18 2026)
```

### Sort Tasks

Sorts tasks by description (case-insensitive).

Command:
`sort`

Expected response:
```
Here are the tasks sorted by description:
1. [T][ ] read book
2. [D][ ] submit report (by: Feb 18 2026)
```

### Exit the App

Closes the application.

Command:
`bye`

Expected response:
```
Bye. Hope to see you again soon!
```

## Error Handling

Tom shows errors when a command is invalid or missing required parts.

Examples:
- Missing description:
  `todo`
- Missing deadline:
  `deadline submit report`
- Invalid task number:
  `mark abc`

In each case, Tom will respond with an error message indicating what went wrong.
