package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class commentsModel {

private int CommentID;
private int userIDComment;
private int checkinID;
private String CommentBody;




public int getCommentID() {
	return CommentID;
}
public void setCommentID(int commentID) {
	CommentID = commentID;
}


public int getUserIDComment() {
	return userIDComment;
}
public void setUserIDComment(int userIDComment) {
	this.userIDComment = userIDComment;
}


public int getCheckinID() {
	return checkinID;
}
public void setCheckinID(int checkinID) {
	this.checkinID = checkinID;
}


public String getCommentBody() {
	return CommentBody;
}
public void setCommentBody(String commentBody) {
	CommentBody = commentBody;
}


public static commentsModel addComment(int userIDComment,int checkinID,String CommentBody) {
	try {
		Connection conn = DBConnection.getActiveConnection();
		String sql = "Insert into comments(`UserCommentID`,`checkinID`,`commentBody`) VALUES  (?,?,?)";
		// System.out.println(sql);

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1,userIDComment);
		stmt.setInt(2,checkinID);
		stmt.setString(3, CommentBody);
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			commentsModel comment = new commentsModel();
			comment.setCommentID(rs.getInt(1));
			comment.setUserIDComment(userIDComment);
			comment.setCheckinID(checkinID);
			comment.setCommentBody(CommentBody);
			return comment;
		}
		return null;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}


}
