package com.psrt.parsers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVReader;
import com.psrt.containers.CanCSVInfo;
import com.psrt.containers.CanID;
import com.psrt.containers.CanValue.CanValueType;
import com.psrt.main.Main;

public class DictionaryParser {
	String csvFile;
	HashMap<CanID, CanCSVInfo> directory;
	CSVReader reader;
	int num_entries_active;
	
	boolean debug = true;
	
	public static final String CAN_ID_STRING = "CAN ID";
	public static final String CAN_FUNCTION_STRING = "Function";
	public static final String CAN_ENTRY_STRING = "Entry";
	
	private InputStream stream;
	
	public DictionaryParser(String csvFile) throws FileNotFoundException, URISyntaxException{
		this.csvFile = csvFile;
		this.num_entries_active = -1;
		directory = new HashMap<CanID, CanCSVInfo>(100);
		stream = Main.class.getResourceAsStream(csvFile);
		reader = new CSVReader(new InputStreamReader(stream));
	}
	
	public void parseDictionary(){
		
		int id_col = -1;
		int function_col = -1;
		int header_row = -1;
		//int num_entries = -1;
		List<String[]> lines = null;
		try {
			lines = reader.readAll();
		} catch (IOException e) {
			log("Dictionary CSV Parser - ERROR - Couldn't read from CSV file at " + csvFile);
			e.printStackTrace();
			return;
		}
		List<Integer> entries_cols = new ArrayList<Integer>();
		
	    for(int i = 0; i < lines.size(); i++) {
	    	String[] line = lines.get(i);
	    	//System.out.println("Row: " + i);
	    	for(int j = 0; j < line.length; j++ ){
				//System.out.println("\tCol: " + j + ", " + line[j]);
				if(line[j].toLowerCase().equals(CAN_ID_STRING.toLowerCase())){
					id_col = j;
				}
				else if(line[j].toLowerCase().equals(CAN_FUNCTION_STRING.toLowerCase())){
					function_col = j;
					header_row = i;
				}else if(line[j].toLowerCase().contains(CAN_ENTRY_STRING.toLowerCase())){
					entries_cols.add(j);
				}
	        }
	    }
	    
	    for(int row = header_row + 1; row < lines.size(); row++){
	    	String[] line = lines.get(row);
	    	CanID id = new CanID();
	    	CanValueType type = null;
	    	int start_index = -1;
	    	for(int col = 0; col < line.length; col++){
	    		
	    		try{
	    			if(col == id_col){
		    			id.id = Integer.parseInt(line[col]);
		    		}else if(col == function_col){
		    			id.function = Integer.parseInt(line[col]);
		    		}else{
		    			for(int entry = 0; entry < entries_cols.size(); entry++){
		    				if(col == entries_cols.get(entry)){
		    					id.entry = entry + 1;
		    					
		    					String data_type = line[col].toLowerCase();
		    					String start_index_str = line[col + 1];
		    					start_index = Integer.parseInt(start_index_str);
		    					start_index += 3;
		    					
		    					if(data_type.equals("f")){
		    						type = CanValueType.FLOAT;
		    					}else if(data_type.equals("b")){
		    						type = CanValueType.BYTE;
		    					}else if(data_type.equals("i") || data_type.equals("int")){
		    						type = CanValueType.INT;
		    					}
		    					if(id.id != -1 && id.function != -1 && id.entry != -1 && type != null && start_index != -1){
					    			log("Row: " + (row + 1) + "; ID: " + id.id + ", " + id.function + ", " + id.entry + ";" + type.toString() + "; " + start_index);
					    			num_entries_active = (id.entry > num_entries_active) ? id.entry : num_entries_active;
					    			directory.put(id, new CanCSVInfo(type, start_index));
					    			type = null;
					    			id = new CanID(id.id, id.function, -1);
					    		}
		    				}
		    			}
		    		}
	    		}catch (NumberFormatException e){
	    			//log("Dictionary CSV Parser - ERROR - ID or function not a proper integer - Row: " + row + ", Col: " + col);
	    		}	
    			//if(type != null) log("Row: " + (row + 1) + "; ID: " + id.id + ", " + id.function + ", " + id.entry + ";" + type.toString());
	    	}
	    }
	    if(id_col == -1){
	    	log("Dictionary CSV Parser - ERROR - ID col not found");
	    }
	    if(function_col == -1){
	    	log("Dictionary CSV Parser - ERROR - Function col not found");
	    }
	    if(header_row == -1){
	    	log("Dictionary CSV Parser - ERROR - Header row not found");
	    }
	}
	
	public HashMap<CanID, CanCSVInfo> getParsedDictionary(){
		return this.directory;
	}
	
	public int numActiveEntries(){
		return this.num_entries_active;
	}
	
	public void close() throws IOException{
		this.stream.close();
	}
	
	private void log(String s){
		if(debug) System.out.println(s);
	}
}
