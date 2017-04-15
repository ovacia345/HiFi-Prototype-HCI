package nl.ru.ai.Interface;

import java.util.ArrayList;

public class DataVector {
	public ArrayList<Double> vector;
	public Polygon_t classification;

	public DataVector() {
		vector = new ArrayList<Double>();
		classification = null;
	}

	public DataVector( ArrayList<Double> vector, Polygon_t classification ) {
		this.vector = vector;
		this.classification = classification;
	}

	public DataVector( DataVector otherVector ) {
		this.vector = new ArrayList<Double>( otherVector.vector );
		this.classification = otherVector.classification;
	}

	public double getLength() {
		double sumSq = 0.0d;

		for ( Double v : vector ) {
			sumSq += v * v;
		}

		return Math.sqrt( sumSq );
	}

	public double getDistance( DataVector otherVector ) {
		double sum = 0.0d;

		for ( int i = 0; i < otherVector.vector.size(); ++i ) {
			sum += ( vector.get( i ) - otherVector.vector.get( i ) ) * ( vector.get( i ) - otherVector.vector.get( i ) );
		}

		return sum;
	}

	public void normalizeVector() {
		double length = this.getLength();

		for ( int i = 0; i < vector.size(); ++i ) {
			vector.set( i, this.vector.get( i ) / length );
		}
	}
}
