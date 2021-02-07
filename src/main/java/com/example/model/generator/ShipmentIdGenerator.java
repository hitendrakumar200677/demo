package com.example.model.generator;

import java.io.Serializable;
import java.util.UUID;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ShipmentIdGenerator implements IdentifierGenerator {

@Override
public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
	// TODO Auto-generated method stub
	/*String prefix = "FFFFFF-";
    Connection connection = session.connection();

    try {
        Statement statement=connection.createStatement();

        ResultSet rs=statement.executeQuery("select count(shipment_id) as Id from shipments");

        if(rs.next())
        {
            int id=rs.getInt(1)+101;
            String generatedId = prefix + new Integer(id).toString();
            return generatedId;
        }
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    return null;*/
	
	UUID uuid=UUID.randomUUID(); //Generates random UUID  
	System.out.println(uuid);
	return uuid.toString();
}
}