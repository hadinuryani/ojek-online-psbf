package com.ojek.online.config;

import com.ojek.online.entity.*;
import com.ojek.online.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final AdminRepository adminRepository;
    private final RideStatusRepository rideStatusRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final LocationRepository locationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Initialize Roles
        if (roleRepository.count() == 0) {
            roleRepository.save(Role.builder().name("ADMIN").description("Administrator").build());
            roleRepository.save(Role.builder().name("DRIVER").description("Driver").build());
            roleRepository.save(Role.builder().name("CUSTOMER").description("Customer").build());
        }

        // Initialize Admin User
        if (userRepository.count() == 0) {
            User adminUser = User.builder()
                    .username("admin")
                    .email("admin@ojek.com")
                    .password(passwordEncoder.encode("admin123"))
                    .fullName("System Administrator")
                    .phone("08123456789")
                    .isActive(true)
                    .build();
            adminUser = userRepository.save(adminUser);

            Role adminRole = roleRepository.findByName("ADMIN").orElseThrow();
            userRoleRepository.save(UserRole.builder().user(adminUser).role(adminRole).build());

            adminRepository.save(Admin.builder()
                    .user(adminUser)
                    .department("IT")
                    .level("Super Admin")
                    .responsibilities("System Management")
                    .build());
        }

        // Initialize Ride Statuses
        if (rideStatusRepository.count() == 0) {
            rideStatusRepository.save(RideStatus.builder().name("PENDING").description("Menunggu driver").build());
            rideStatusRepository.save(RideStatus.builder().name("ACCEPTED").description("Driver menerima").build());
            rideStatusRepository.save(RideStatus.builder().name("ON_GOING").description("Perjalanan berlangsung").build());
            rideStatusRepository.save(RideStatus.builder().name("COMPLETED").description("Perjalanan selesai").build());
            rideStatusRepository.save(RideStatus.builder().name("CANCELLED").description("Dibatalkan").build());
        }

        // Initialize Payment Methods
        if (paymentMethodRepository.count() == 0) {
            paymentMethodRepository.save(PaymentMethod.builder().name("CASH").description("Bayar Tunai").isActive(true).build());
            paymentMethodRepository.save(PaymentMethod.builder().name("WALLET").description("Saldo Wallet").isActive(true).build());
            paymentMethodRepository.save(PaymentMethod.builder().name("BANK_TRANSFER").description("Transfer Bank").isActive(true).build());
        }

        // Initialize Vehicle Types
        if (vehicleTypeRepository.count() == 0) {
            vehicleTypeRepository.save(VehicleType.builder().name("MOTOR").description("Sepeda Motor").basePrice(5000.0).pricePerKm(2000.0).build());
            vehicleTypeRepository.save(VehicleType.builder().name("MOBIL").description("Mobil Sedan/Hatchback").basePrice(10000.0).pricePerKm(4000.0).build());
            vehicleTypeRepository.save(VehicleType.builder().name("MOBIL_XL").description("MPV / SUV").basePrice(15000.0).pricePerKm(5000.0).build());
        }

        // Initialize Locations
        if (locationRepository.count() == 0) {

            // ── Jakarta ──────────────────────────────────────────────────
            locationRepository.save(Location.builder().name("Monas").address("Jl. Merdeka Selatan, Jakarta Pusat").city("Jakarta").latitude(-6.1754).longitude(106.8272).build());
            locationRepository.save(Location.builder().name("Blok M").address("Jl. Melawai, Kebayoran Baru").city("Jakarta").latitude(-6.2446).longitude(106.7983).build());
            locationRepository.save(Location.builder().name("Kota Tua").address("Jl. Taman Fatahillah, Jakarta Barat").city("Jakarta").latitude(-6.1352).longitude(106.8133).build());
            locationRepository.save(Location.builder().name("Senayan").address("Jl. Asia Afrika, Gelora").city("Jakarta").latitude(-6.2191).longitude(106.8020).build());
            locationRepository.save(Location.builder().name("Kemang").address("Jl. Kemang Raya, Jakarta Selatan").city("Jakarta").latitude(-6.2612).longitude(106.8134).build());
            locationRepository.save(Location.builder().name("Menteng").address("Jl. Diponegoro, Menteng").city("Jakarta").latitude(-6.1986).longitude(106.8394).build());
            locationRepository.save(Location.builder().name("Sudirman").address("Jl. Jend. Sudirman, Jakarta Pusat").city("Jakarta").latitude(-6.2088).longitude(106.8219).build());
            locationRepository.save(Location.builder().name("Tanah Abang").address("Jl. Tanah Abang I, Jakarta Pusat").city("Jakarta").latitude(-6.1863).longitude(106.8113).build());
            locationRepository.save(Location.builder().name("Tebet").address("Jl. Tebet Raya, Jakarta Selatan").city("Jakarta").latitude(-6.2258).longitude(106.8528).build());
            locationRepository.save(Location.builder().name("Cikini").address("Jl. Cikini Raya, Menteng").city("Jakarta").latitude(-6.1918).longitude(106.8426).build());

            // ── Surabaya – Pusat Perbelanjaan ────────────────────────────
            locationRepository.save(Location.builder().name("Tunjungan Plaza").address("Jl. Basuki Rahmat No.8-12, Embong Kaliasin").city("Surabaya").latitude(-7.2619).longitude(112.7404).build());
            locationRepository.save(Location.builder().name("Grand City Mall").address("Jl. Walikota Mustajab No.1, Ketabang").city("Surabaya").latitude(-7.2537).longitude(112.7506).build());
            locationRepository.save(Location.builder().name("Galaxy Mall").address("Jl. Dharmahusada Indah Timur No.35, Mojo").city("Surabaya").latitude(-7.2709).longitude(112.7742).build());
            locationRepository.save(Location.builder().name("Pakuwon Mall").address("Jl. Puncak Indah Lontar No.2, Lontar").city("Surabaya").latitude(-7.2776).longitude(112.6625).build());
            locationRepository.save(Location.builder().name("Ciputra World Surabaya").address("Jl. Mayjen Sungkono No.89, Dukuh Pakis").city("Surabaya").latitude(-7.2920).longitude(112.7188).build());

            // ── Surabaya – Transportasi ──────────────────────────────────
            locationRepository.save(Location.builder().name("Stasiun Gubeng").address("Jl. Gubeng Masjid No.1, Pacar Keling").city("Surabaya").latitude(-7.2654).longitude(112.7524).build());
            locationRepository.save(Location.builder().name("Stasiun Pasar Turi").address("Jl. Semarang No.1, Tembok Dukuh").city("Surabaya").latitude(-7.2393).longitude(112.7276).build());
            locationRepository.save(Location.builder().name("Bandara Juanda").address("Jl. Ir. H. Juanda, Semambung, Sidoarjo").city("Surabaya").latitude(-7.3797).longitude(112.7869).build());
            locationRepository.save(Location.builder().name("Pelabuhan Tanjung Perak").address("Jl. Tanjung Perak Timur, Krembangan Utara").city("Surabaya").latitude(-7.2003).longitude(112.7325).build());
            locationRepository.save(Location.builder().name("Terminal Bungurasih").address("Jl. Jend. A. Yani No.268, Waru, Sidoarjo").city("Surabaya").latitude(-7.3507).longitude(112.7362).build());

            // ── Surabaya – Kesehatan ─────────────────────────────────────
            locationRepository.save(Location.builder().name("RSUD Dr. Soetomo").address("Jl. Mayjend Prof. Dr. Moestopo No.6-8, Airlangga").city("Surabaya").latitude(-7.2666).longitude(112.7608).build());
            locationRepository.save(Location.builder().name("RS Siloam Surabaya").address("Jl. Raya Gubeng No.70, Gubeng").city("Surabaya").latitude(-7.2642).longitude(112.7512).build());

            // ── Surabaya – Pendidikan ────────────────────────────────────
            locationRepository.save(Location.builder().name("Universitas Airlangga").address("Jl. Airlangga No.4-6, Airlangga").city("Surabaya").latitude(-7.2675).longitude(112.7680).build());
            locationRepository.save(Location.builder().name("ITS Sukolilo").address("Jl. Raya ITS, Keputih, Sukolilo").city("Surabaya").latitude(-7.2819).longitude(112.7956).build());
            locationRepository.save(Location.builder().name("Universitas Negeri Surabaya").address("Jl. Ketintang, Ketintang").city("Surabaya").latitude(-7.3133).longitude(112.7286).build());

            // ── Surabaya – Wisata & Ruang Publik ─────────────────────────
            locationRepository.save(Location.builder().name("Kebun Binatang Surabaya").address("Jl. Setail No.1, Darmo").city("Surabaya").latitude(-7.2948).longitude(112.7372).build());
            locationRepository.save(Location.builder().name("Taman Bungkul").address("Jl. Raya Darmo, Darmo").city("Surabaya").latitude(-7.2963).longitude(112.7362).build());
            locationRepository.save(Location.builder().name("Kenjeran Park").address("Jl. Kenjeran No.1, Bulak").city("Surabaya").latitude(-7.2217).longitude(112.7889).build());
            locationRepository.save(Location.builder().name("House of Sampoerna").address("Taman Sampoerna No.6, Krembangan Utara").city("Surabaya").latitude(-7.2313).longitude(112.7314).build());
            locationRepository.save(Location.builder().name("Monumen Kapal Selam").address("Jl. Pemuda No.39, Embong Kaliasin").city("Surabaya").latitude(-7.2572).longitude(112.7421).build());

            // ── Surabaya – Kawasan & Pasar ───────────────────────────────
            locationRepository.save(Location.builder().name("Pasar Atom").address("Jl. Bunguran No.45, Bongkaran").city("Surabaya").latitude(-7.2313).longitude(112.7350).build());
            locationRepository.save(Location.builder().name("Jalan Tunjungan").address("Jl. Tunjungan, Genteng").city("Surabaya").latitude(-7.2567).longitude(112.7370).build());
            locationRepository.save(Location.builder().name("Wonokromo").address("Jl. Wonokromo, Wonokromo").city("Surabaya").latitude(-7.3082).longitude(112.7369).build());
            locationRepository.save(Location.builder().name("Rungkut Industri").address("Jl. Rungkut Industri Raya, Rungkut").city("Surabaya").latitude(-7.3298).longitude(112.7821).build());
            locationRepository.save(Location.builder().name("Pasar Turi").address("Jl. Semarang No.1, Tembok Dukuh").city("Surabaya").latitude(-7.2380).longitude(112.7282).build());
        }
    }
}