package edu.mum.cs.cs425.ahacarrentalservice.repository;

import edu.mum.cs.cs425.ahacarrentalservice.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByCarProfileOrderByIdAsc(CarProfile carProfile);

    @Query("select o from Offer o where o.publicationStatus=:publicationStatus and o.carProfile.status=:analysisStatus")
    List<Offer> findByPublicationStatusAndAnalysisStatusCarProfile(@Param("publicationStatus") Boolean publicationStatus,
                                                                   @Param("analysisStatus") AnalysisStatus analysisStatus);

    @Query("select o from Offer  o where o.publicationStatus=:publicationStatus and o.carProfile.year=:year and o.carProfile.status=:analysisStatus")
    List<Offer> findByYearCarProfileAndAnalysisStatusCarProfile(@Param("publicationStatus") Boolean publicationStatus,
                                                                @Param("year") Integer year,
                                                                @Param("analysisStatus") AnalysisStatus analysisStatus);

    @Query("select o from Offer  o where o.publicationStatus=:publicationStatus and o.carProfile.model=:carModel and o.carProfile.status=:analysisStatus")
    List<Offer> findByModelCarProfileAndAnalysisStatusCarProfile(@Param("publicationStatus") Boolean publicationStatus,
                                                                @Param("carModel")CarModel carModel,
                                                                 @Param("analysisStatus") AnalysisStatus analysisStatus);

    @Query("select o from Offer  o where o.publicationStatus=:publicationStatus and o.carProfile.model.brand=:carBrand and o.carProfile.status=:analysisStatus")
    List<Offer> findByBrandCarProfileAndAnalysisStatusCarProfile(@Param("publicationStatus") Boolean publicationStatus,
                                                                 @Param("carBrand")CarBrand carBrand,
                                                                 @Param("analysisStatus") AnalysisStatus analysisStatus);

    @Query("select o from Offer  o where o.publicationStatus=:publicationStatus and o.carProfile.year=:year and o.carProfile.model=:carModel and o.carProfile.status=:analysisStatus")
    List<Offer> findByYearAndModelCarProfileAndAnalysisStatusCarProfile(@Param("publicationStatus") Boolean publicationStatus,
                                                                        @Param("year")Integer year,
                                                                        @Param("carModel")CarModel carModel,
                                                                        @Param("analysisStatus") AnalysisStatus analysisStatus);

    @Query("select o from Offer  o where o.publicationStatus=:publicationStatus and o.carProfile.year=:year and o.carProfile.model.brand=:carBrand and o.carProfile.status=:analysisStatus")
    List<Offer> findByYearAndBrandCarProfileAndAnalysisStatusCarProfile(@Param("publicationStatus") Boolean publicationStatus,
                                                                        @Param("year")Integer year,
                                                                        @Param("carBrand")CarBrand carBrand,
                                                                        @Param("analysisStatus") AnalysisStatus analysisStatus);

}
