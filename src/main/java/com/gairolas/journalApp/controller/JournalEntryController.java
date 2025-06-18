// package com.gairolas.journalApp.controller;

// import com.gairolas.journalApp.entity.JournalEntry;

// import java.util.List;
// import java.util.Map;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.ArrayList;
// import java.util.HashMap;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.PutMapping;

// @RestController
// @RequestMapping("/journal")
// public class JournalEntryController {
//     private Map<String, JournalEntry> journalEntries = new HashMap<>();

//     @GetMapping
//     public List<JournalEntry> getAll() {
//         return new ArrayList<>(journalEntries.values());
//     }

//     @PostMapping
//     public boolean createEntry(@RequestBody JournalEntry entry) {
//         journalEntries.put(entry.getId(), entry);
//         return true;
//     }

//     @GetMapping("id/{myId}")
//     public JournalEntry getEntry(@PathVariable String myId) {
//         return journalEntries.get(myId);
//     }

//     @DeleteMapping("id/{myId}")
//     public JournalEntry deleteEntry(@PathVariable String myId) {
//         return journalEntries.remove(myId);
//     }

//     @PutMapping("id/{myId}")
//     public JournalEntry updateEntry(@PathVariable String myId, @RequestBody JournalEntry entry) {
//         return journalEntries.put(myId, entry);
//     }
// }