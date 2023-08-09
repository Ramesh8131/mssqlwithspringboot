package com.rigel.service;

import java.util.concurrent.TimeUnit;

import org.apache.plc4x.java.PlcDriverManager;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.exceptions.PlcConnectionException;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.messages.PlcReadResponse;
import org.apache.plc4x.java.api.messages.PlcWriteRequest;
import org.apache.plc4x.java.api.messages.PlcWriteResponse;
import org.apache.plc4x.java.api.types.PlcResponseCode;

import lombok.extern.log4j.Log4j2;

//@Service
@Log4j2
public class ReadPLCData {
	
	public static void main(String[] args) {
		try {
			new ReadPLCData().read();
		} catch (PlcConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void read() throws PlcConnectionException, Exception {
		
		String connectionString = "modbus-tcp:tcp://127.0.0.1:502";//"modbus:tcp://127.0.0.1";

		try (PlcConnection plcConnection = new PlcDriverManager().getConnection(connectionString)) {

			  //write  data
			  if (!plcConnection.getMetadata().canWrite()) {
				  log.error("This connection doesn't support writing.");
				  return;
			  }else {
//				  PlcWriteRequest.Builder builder1 = plcConnection.writeRequestBuilder();
//				  builder1.addItem("value-1", "000006:BOOL[1]",false);
////				  builder1.addItem("st1", "400011:UINT[1]",55); /// holding register
//				  
//				  PlcWriteRequest writeRequest = builder1.build();
//				  PlcWriteResponse response1 = writeRequest.execute().get();
//				  for (String fieldName : response1.getFieldNames()) {
//					    if(response1.getResponseCode(fieldName) == PlcResponseCode.OK) {
//					        log.info("Value[" + fieldName + "]: updated");
//					    }
//					    // Something went wrong, to output an error message instead.
//					    else {
//					        log.error("Error[" + fieldName + "]: " + response1.getResponseCode(fieldName).name());
//					    }
//					}
			  }
			
			
			if (!plcConnection.getMetadata().canRead()) {
				  log.error("This connection doesn't support reading.");
				  return;
			}else {
				PlcReadRequest.Builder builder = plcConnection.readRequestBuilder();
				builder.addItem("value-1", "6:BYTE[8]");
//				builder.addItem("st1", "400011:UINT[1]");
//				builder.addItem("st2", "%Q0.5:BOOL");
//				builder.addItem("value-2", "%Q0:BYTE");
//				builder.addItem("value-3", "%I0.2:BOOL");
//				builder.addItem("value-4", "%DB.DB1.4:INT");
				PlcReadRequest readRequest = builder.build();
				PlcReadResponse response = readRequest.execute().get(5000, TimeUnit.MILLISECONDS);
//				CompletableFuture<? extends PlcReadResponse> asyncResponse = readRequest.execute();
//				asyncResponse.whenComplete((response, throwable) -> {
				  try {
//					  PlcReadResponse response = readRequest.execute().get(5000, TimeUnit.MILLISECONDS);
					  for (String fieldName : response.getFieldNames()) {
						    if(response.getResponseCode(fieldName) == PlcResponseCode.OK) {
						        int numValues = response.getNumberOfValues(fieldName);
						        // If it's just one element, output just one single line.
						        if(numValues == 1) {
						            log.info("Value[" + fieldName + "]: " + response.getObject(fieldName));
						        }
						        // If it's more than one element, output each in a single row.
						        else {
						            log.info("Value[" + fieldName + "]:");
						            for(int i = 0; i < numValues; i++) {
						                log.info(" - " + response.getObject(fieldName, i));
						            }
						        }
						    }
						    // Something went wrong, to output an error message instead.
						    else {
						        log.error("Error[" + fieldName + "]: " + response.getResponseCode(fieldName).name());
						    }
						}
					  System.out.println(response);
				  } catch (Exception e) {
				  }
				  
				  
				
				  
//				});
			}
		
		}
	}

}
